package com.weibo.dip.analysisql.dsl.filter.relational.le;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** DoubleLeFilter. */
public class DoubleLeFilter extends LeFilter {
  private double value;

  public DoubleLeFilter() {}

  /**
   * Initializes a instance with name and double value.
   *
   * @param name name
   * @param value double value
   */
  public DoubleLeFilter(String name, double value) {
    super(name, RelationalFilter.STRING);

    this.value = value;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
