package com.weibo.dip.analysisql.dsl.filter.relational.ge;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** DoubleGeFilter. */
public class DoubleGeFilter extends GeFilter {
  private double value;

  public DoubleGeFilter() {}

  /**
   * Initializes a instance with name and double value.
   *
   * @param name name
   * @param value double value
   */
  public DoubleGeFilter(String name, double value) {
    super(name, RelationalFilter.DOUBLE);

    this.value = value;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
