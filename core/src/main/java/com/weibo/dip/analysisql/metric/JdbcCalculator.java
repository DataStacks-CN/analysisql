package com.weibo.dip.analysisql.metric;

import com.weibo.dip.analysisql.metric.SqlFileBasedCalculator;
import com.weibo.dip.analysisql.metric.SqlTemplate;
import com.weibo.dip.analysisql.response.Row;
import com.weibo.dip.analysisql.response.column.Column;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** JdbcCalculator. */
public abstract class JdbcCalculator extends SqlFileBasedCalculator {
  protected String url;
  protected String user;
  protected String passwd;

  /**
   * Initialize a instance with sql template, sql file, url, user, password.
   *
   * @param sqlTemplate SqlTemplate
   * @param sqlFile sql file
   * @param url jdbc url
   * @param user jdbc user
   * @param passwd jdbc password
   */
  public JdbcCalculator(
      SqlTemplate sqlTemplate, String sqlFile, String url, String user, String passwd) {
    super(sqlTemplate, sqlFile);

    this.url = url;
    this.user = user;
    this.passwd = passwd;
  }

  public abstract Column<?> cast(ResultSet rs, int column, String columnName, String columnTypeName)
      throws SQLException;

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

          cols.add(cast(rs, column, columnName, columnTypeName));
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
