package com.weibo.dip.analysisql.response.column;

/** LongStringColumn. */
public class ArrayLongColumn extends Column<long[]> {
  public ArrayLongColumn() {}

  /**
   * Initialize a instance with name and long array.
   *
   * @param name name
   * @param values long array
   */
  public ArrayLongColumn(String name, long[] values) {
    super(name, Column.ARRAY_LONG, values);
  }
}
