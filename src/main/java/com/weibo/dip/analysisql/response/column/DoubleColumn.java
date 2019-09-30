package com.weibo.dip.analysisql.response.column;

/** DoubleColumn. */
public class DoubleColumn extends Column<Double> {
  public DoubleColumn() {}

  /**
   * Initializes a instance with name and double value.
   *
   * @param name name
   * @param value double value
   */
  public DoubleColumn(String name, double value) {
    super(name, Column.DOUBLE, value);
  }
}
