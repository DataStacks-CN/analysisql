package com.weibo.dip.analysisql.mysql.metric;

import com.weibo.dip.analysisql.metric.JdbcCalculator;
import com.weibo.dip.analysisql.metric.SqlTemplate;
import com.weibo.dip.analysisql.metric.SqlTemplateFactory;
import com.weibo.dip.analysisql.response.column.Column;
import com.weibo.dip.analysisql.response.column.DoubleColumn;
import com.weibo.dip.analysisql.response.column.LongColumn;
import com.weibo.dip.analysisql.response.column.StringColumn;
import java.sql.ResultSet;
import java.sql.SQLException;

/** MySqlCalculator. */
public class MySqlCalculator extends JdbcCalculator {
  public MySqlCalculator(
      SqlTemplateFactory sqlTemplateFactory,
      String sqlFile,
      String url,
      String user,
      String passwd) {
    super(sqlTemplateFactory, sqlFile, url, user, passwd);
  }

  @Override
  public Column<?> cast(ResultSet rs, int column, String columnName, String columnTypeName)
      throws SQLException {
    switch (columnTypeName.toUpperCase()) {
      case "VARCHAR":
      case "CHAR":
        if (columnName.equals(SqlTemplate.TIME_BUCKET)) {
          return new LongColumn(columnName, rs.getTimestamp(column).getTime());
        } else {
          return new StringColumn(columnName, rs.getString(column));
        }

      case "TINYINT":
      case "SMALLINT":
      case "MEDIUMINT":
      case "INT":
      case "BIGINT":
        return new LongColumn(columnName, rs.getLong(column));

      case "FLOAT":
      case "DOUBLE":
        return new DoubleColumn(columnName, rs.getDouble(column));

      case "DECIMAL":
        return new DoubleColumn(columnName, rs.getBigDecimal(column).doubleValue());

      default:
        throw new UnsupportedOperationException(columnTypeName);
    }
  }
}
