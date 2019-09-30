package com.weibo.dip.analysisql.response.column;

/** LongColumn. */
public class LongColumn extends Column<Long> {
  public LongColumn() {}

  public LongColumn(String name, long value) {
    super(name, Column.LONG, value);
  }
}
