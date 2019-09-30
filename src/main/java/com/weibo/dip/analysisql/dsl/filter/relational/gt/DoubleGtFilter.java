package com.weibo.dip.analysisql.dsl.filter.relational.gt;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** DoubleGtRelationalFilter. */
public class DoubleGtFilter extends GtFilter {
  private double value;

  public DoubleGtFilter() {}

  /**
   * Initializes a instance with name and double value.
   *
   * @param name name
   * @param value double value
   */
  public DoubleGtFilter(String name, double value) {
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
