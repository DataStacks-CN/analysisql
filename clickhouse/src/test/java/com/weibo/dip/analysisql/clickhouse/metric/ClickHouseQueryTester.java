package com.weibo.dip.analysisql.clickhouse.metric;

import com.weibo.dip.analysisql.response.column.Column;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.clickhouse.domain.ClickHouseDataType;

/** ClickHouseQueryTester. */
public class ClickHouseQueryTester {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClickHouseQueryTester.class);

  public static void main(String[] args) throws Exception {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      conn = DriverManager.getConnection(args[0], args[1], args[2]);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(args[3]);

      ResultSetMetaData metaData = rs.getMetaData();

      int columns = metaData.getColumnCount();
      if (columns == 0) {
        return;
      }

      List<String> columnNames = new ArrayList<>(columns);
      List<String> columnTypeNames = new ArrayList<>(columns);

      for (int column = 1; column <= columns; column++) {
        columnNames.add(metaData.getColumnName(column));
        columnTypeNames.add(metaData.getColumnTypeName(column));
      }

      while (rs.next()) {
        List<Column<?>> cols = new ArrayList<>(columns);

        for (int column = 1; column <= columns; column++) {
          String columnName = columnNames.get(column - 1);
          String columnTypeName = columnTypeNames.get(column - 1);

          LOGGER.info(
              "name: {}, type: {}, value: {}", columnName, columnTypeName, rs.getObject(column));
        }
      }
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

    System.out.println(ClickHouseDataType.fromTypeString("Int64").equals(ClickHouseDataType.Int64));
  }
}
