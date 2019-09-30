package com.weibo.dip.analysisql.dsl.filter.relational.lt;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** DoubleLtFilter. */
public class DoubleLtFilter extends LtFilter {
  private double value;

  public DoubleLtFilter() {}

  /**
   * Initializes a instance with name and double value.
   *
   * @param name name
   * @param value double value
   */
  public DoubleLtFilter(String name, double value) {
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
