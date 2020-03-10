package com.weibo.dip.analysisql.presto.metric;

import com.weibo.dip.analysisql.metric.JdbcCalculator;
import com.weibo.dip.analysisql.metric.SqlTemplate;
import com.weibo.dip.analysisql.response.column.ArrayDoubleColumn;
import com.weibo.dip.analysisql.response.column.ArrayLongColumn;
import com.weibo.dip.analysisql.response.column.ArrayStringColumn;
import com.weibo.dip.analysisql.response.column.Column;
import com.weibo.dip.analysisql.response.column.DoubleColumn;
import com.weibo.dip.analysisql.response.column.LongColumn;
import com.weibo.dip.analysisql.response.column.StringColumn;
import java.sql.ResultSet;
import java.sql.SQLException;

/** PrestoCalculator. */
public class PrestoCalculator extends JdbcCalculator {
  public PrestoCalculator(
      SqlTemplate sqlTemplate, String sqlFile, String url, String user, String passwd) {
    super(sqlTemplate, sqlFile, url, user, passwd);
  }

  @Override
  public Column<?> cast(ResultSet rs, int column, String columnName, String columnTypeName)
      throws SQLException {
    switch (columnTypeName.toUpperCase()) {
      case "VARCHAR":
      case "CHAR":
        return new StringColumn(columnName, rs.getString(column));

      case "TINYINT":
      case "SMALLINT":
      case "INTEGER":
      case "BIGINT":
        return new LongColumn(columnName, rs.getLong(column));

      case "REAL":
      case "DOUBLE":
        return new DoubleColumn(columnName, rs.getDouble(column));

      case "ARRAY(VARCHAR)":
      case "ARRAY(CHAR)":
        Object[] stringArray = (Object[]) rs.getArray(column).getArray();

        String[] stringValues = new String[stringArray.length];

        for (int cindex = 0; cindex < stringArray.length; cindex++) {
          stringValues[cindex] = stringArray[cindex].toString();
        }

        return new ArrayStringColumn(columnName, stringValues);

      case "ARRAY(TINYINT)":
      case "ARRAY(SMALLINT)":
      case "ARRAY(INTEGER)":
      case "ARRAY(BIGINT)":
        Object[] longArray = (Object[]) rs.getArray(column).getArray();

        long[] longValues = new long[longArray.length];

        for (int cindex = 0; cindex < longArray.length; cindex++) {
          longValues[cindex] = Long.valueOf(longArray[cindex].toString());
        }

        return new ArrayLongColumn(columnName, longValues);

      case "ARRAY(REAL)":
      case "ARRAY(DOUBLE)":
        Object[] doubleArray = (Object[]) rs.getArray(column).getArray();

        double[] doubleValues = new double[doubleArray.length];

        for (int cindex = 0; cindex < doubleArray.length; cindex++) {
          doubleValues[cindex] = Double.valueOf(doubleArray[cindex].toString());
        }

        return new ArrayDoubleColumn(columnName, doubleValues);

      default:
        throw new UnsupportedOperationException(columnTypeName);
    }
  }
}
