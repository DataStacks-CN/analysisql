package com.weibo.dip.analysisql.clickhouse.metric;

import com.weibo.dip.analysisql.metric.JdbcCalculator;
import com.weibo.dip.analysisql.metric.SqlTemplate;
import com.weibo.dip.analysisql.metric.SqlTemplateFactory;
import com.weibo.dip.analysisql.response.column.Column;
import com.weibo.dip.analysisql.response.column.DoubleColumn;
import com.weibo.dip.analysisql.response.column.LongColumn;
import com.weibo.dip.analysisql.response.column.StringColumn;
import java.sql.ResultSet;
import java.sql.SQLException;
import ru.yandex.clickhouse.domain.ClickHouseDataType;

/** ClickHouseCalculator. */
public class ClickHouseCalculator extends JdbcCalculator {
  public ClickHouseCalculator(
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
    switch (ClickHouseDataType.fromTypeString(columnTypeName)) {
      case String:
      case FixedString:
        return new StringColumn(columnName, rs.getString(column));

      case UInt8:
      case UInt16:
      case UInt32:
      case UInt64:
      case Int8:
      case Int16:
      case Int32:
      case Int64:
        return new LongColumn(columnName, rs.getLong(column));

      case Float32:
      case Float64:
        return new DoubleColumn(columnName, rs.getDouble(column));
      default:
        if (columnTypeName.contains(ClickHouseDataType.DateTime.name())) {
          return new LongColumn(columnName, rs.getTimestamp(column).getTime());
        }

        throw new UnsupportedOperationException(columnTypeName);
    }
  }
}
