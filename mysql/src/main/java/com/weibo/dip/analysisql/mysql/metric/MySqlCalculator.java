package com.weibo.dip.analysisql.mysql.metric;

import com.weibo.dip.analysisql.metric.SqlFileBasedCalculator;
import com.weibo.dip.analysisql.metric.SqlTemplate;
import com.weibo.dip.analysisql.response.Row;
import com.weibo.dip.analysisql.response.column.Column;
import com.weibo.dip.analysisql.response.column.DoubleColumn;
import com.weibo.dip.analysisql.response.column.LongColumn;
import com.weibo.dip.analysisql.response.column.StringColumn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** MySqlCalculator. */
public class MySqlCalculator extends SqlFileBasedCalculator {
  private String url;
  private String user;
  private String passwd;

  /**
   * Initialize a instance with sql template, sql file, url, user, password.
   *
   * @param sqlTemplate SqlTemplate
   * @param sqlFile sql file
   * @param url mysql url
   * @param user mysql user
   * @param passwd mysql password
   */
  public MySqlCalculator(
      SqlTemplate sqlTemplate, String sqlFile, String url, String user, String passwd) {
    super(sqlTemplate, sqlFile);

    this.url = url;
    this.user = user;
    this.passwd = passwd;
  }

  @Override
  public List<Row> query(String sql) throws Exception {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      conn = DriverManager.getConnection(url, user, passwd);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);

      ResultSetMetaData metaData = rs.getMetaData();

      int columns = metaData.getColumnCount();
      if (columns == 0) {
        return null;
      }

      List<String> columnNames = new ArrayList<>(columns);
      List<String> columnTypeNames = new ArrayList<>(columns);

      for (int column = 1; column <= columns; column++) {
        columnNames.add(metaData.getColumnName(column));
        columnTypeNames.add(metaData.getColumnTypeName(column));
      }

      List<Row> rows = new ArrayList<>();

      while (rs.next()) {
        List<Column<?>> cols = new ArrayList<>(columns);

        for (int column = 1; column <= columns; column++) {
          String columnName = columnNames.get(column - 1);
          String columnTypeName = columnTypeNames.get(column - 1);

          switch (columnTypeName) {
            case "TIMESTAMP":
              assert columnName.equals(SqlTemplate.TIME_BUCKET);

              cols.add(new LongColumn(columnName, rs.getTimestamp(column).getTime()));
              break;

            case "VARCHAR":
            case "CHAR":
              cols.add(new StringColumn(columnName, rs.getString(column)));
              break;

            case "TINYINT":
            case "SMALLINT":
            case "MEDIUMINT":
            case "INT":
            case "BIGINT":
              cols.add(new LongColumn(columnName, rs.getLong(column)));
              break;

            case "FLOAT":
            case "DOUBLE":
              cols.add(new DoubleColumn(columnName, rs.getDouble(column)));
              break;

            default:
              throw new UnsupportedOperationException();
          }
        }

        rows.add(new Row(cols));
      }

      return rows;
    } finally {
      if (Objects.nonNull(rs)) {
        rs.close();
      }

      if (Objects.nonNull(stmt)) {
        stmt.close();
      }

      if (Objects.nonNull(conn)) {
        conn.close();
      }
    }
  }
}
