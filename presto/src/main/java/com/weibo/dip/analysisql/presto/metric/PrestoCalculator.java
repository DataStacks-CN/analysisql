package com.weibo.dip.analysisql.presto.metric;

import com.facebook.presto.jdbc.PrestoConnection;
import com.facebook.presto.jdbc.PrestoResultSet;
import com.facebook.presto.jdbc.PrestoStatement;
import com.weibo.dip.analysisql.metric.SqlFileBasedCalculator;
import com.weibo.dip.analysisql.metric.SqlTemplate;
import com.weibo.dip.analysisql.response.Row;
import com.weibo.dip.analysisql.response.column.ArrayDoubleColumn;
import com.weibo.dip.analysisql.response.column.ArrayLongColumn;
import com.weibo.dip.analysisql.response.column.ArrayStringColumn;
import com.weibo.dip.analysisql.response.column.Column;
import com.weibo.dip.analysisql.response.column.DoubleColumn;
import com.weibo.dip.analysisql.response.column.LongColumn;
import com.weibo.dip.analysisql.response.column.StringColumn;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** PrestoCalculator. */
public class PrestoCalculator extends SqlFileBasedCalculator {
  private String url;
  private String user;
  private String passwd;

  /**
   * Initialize a instance with sql template, sql file, url, user, password.
   *
   * @param sqlTemplate SqlTemplate
   * @param sqlFile sql file
   * @param url presto url
   * @param user presto user
   * @param passwd presto password
   */
  public PrestoCalculator(
      SqlTemplate sqlTemplate, String sqlFile, String url, String user, String passwd) {
    super(sqlTemplate, sqlFile);

    this.url = url;
    this.user = user;
    this.passwd = passwd;
  }

  @Override
  public List<Row> query(String sql) throws Exception {
    PrestoConnection connection = null;
    PrestoStatement statement = null;
    PrestoResultSet resultSet = null;

    try {
      connection = (PrestoConnection) DriverManager.getConnection(url, user, passwd);

      statement = (PrestoStatement) connection.createStatement();

      statement.setQueryTimeout(Integer.MAX_VALUE);
      statement.setMaxRows(Integer.MAX_VALUE);

      resultSet = (PrestoResultSet) statement.executeQuery(sql);

      ResultSetMetaData metaData = resultSet.getMetaData();

      int columns = metaData.getColumnCount();
      if (columns == 0) {
        return null;
      }

      List<String> columnNames = new ArrayList<>(columns);
      List<String> columnTypeNames = new ArrayList<>(columns);

      for (int index = 1; index <= columns; index++) {
        columnNames.add(metaData.getColumnName(index));
        columnTypeNames.add(metaData.getColumnTypeName(index).toUpperCase());
      }

      List<Row> rows = new ArrayList<>();

      while (resultSet.next()) {
        List<Column<?>> cols = new ArrayList<>(columns);

        for (int index = 1; index <= columns; index++) {
          String columnName = columnNames.get(index - 1);

          switch (columnTypeNames.get(index - 1)) {
            case "VARCHAR":
            case "CHAR":
              cols.add(new StringColumn(columnName, resultSet.getString(index)));
              break;

            case "TINYINT":
            case "SMALLINT":
            case "INTEGER":
            case "BIGINT":
              cols.add(new LongColumn(columnName, resultSet.getLong(index)));
              break;

            case "REAL":
            case "DOUBLE":
              cols.add(new DoubleColumn(columnName, resultSet.getDouble(index)));
              break;

            case "ARRAY(VARCHAR)":
            case "ARRAY(CHAR)":
              Object[] stringArray = (Object[]) resultSet.getArray(index).getArray();

              String[] stringValues = new String[stringArray.length];

              for (int cindex = 0; cindex < stringArray.length; cindex++) {
                stringValues[cindex] = stringArray[cindex].toString();
              }

              cols.add(new ArrayStringColumn(columnName, stringValues));
              break;

            case "ARRAY(TINYINT)":
            case "ARRAY(SMALLINT)":
            case "ARRAY(INTEGER)":
            case "ARRAY(BIGINT)":
              Object[] longArray = (Object[]) resultSet.getArray(index).getArray();

              long[] longValues = new long[longArray.length];

              for (int cindex = 0; cindex < longArray.length; cindex++) {
                longValues[cindex] = Long.valueOf(longArray[cindex].toString());
              }

              cols.add(new ArrayLongColumn(columnName, longValues));
              break;

            case "ARRAY(REAL)":
            case "ARRAY(DOUBLE)":
              Object[] doubleArray = (Object[]) resultSet.getArray(index).getArray();

              double[] doubleValues = new double[doubleArray.length];

              for (int cindex = 0; cindex < doubleArray.length; cindex++) {
                doubleValues[cindex] = Double.valueOf(doubleArray[cindex].toString());
              }

              cols.add(new ArrayDoubleColumn(columnName, doubleValues));
              break;

            default:
              throw new UnsupportedOperationException();
          }
        }

        rows.add(new Row(cols));
      }

      return rows;
    } finally {
      if (Objects.nonNull(resultSet)) {
        resultSet.close();
      }

      if (Objects.nonNull(statement)) {
        statement.close();
      }

      if (Objects.nonNull(connection)) {
        connection.close();
      }
    }
  }
}
