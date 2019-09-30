package com.weibo.dip.analysisql.response.column;

/** DoubleColumn. */
public class DoubleColumn extends Column<Double> {
  public DoubleColumn() {}

  public DoubleColumn(String name, double value) {
    super(name, Column.DOUBLE, value);
  }
}
