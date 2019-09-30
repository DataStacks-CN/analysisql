package com.weibo.dip.analysisql.dsl.filter.relational.ne;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** DoubleNeRelationalFilter. */
public class DoubleNeFilter extends NeFilter {
  private double value;

  public DoubleNeFilter() {}

  /**
   * Initializes a instance with name and double value.
   *
   * @param name name
   * @param value double value
   */
  public DoubleNeFilter(String name, double value) {
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
