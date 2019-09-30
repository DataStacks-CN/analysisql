package com.weibo.dip.analysisql.response.column;

/** LongColumn. */
public class LongColumn extends Column<Long> {
  public LongColumn() {}

  /**
   * Initializes a instace with name and long value.
   *
   * @param name name
   * @param value value
   */
  public LongColumn(String name, long value) {
    super(name, Column.LONG, value);
  }
}
