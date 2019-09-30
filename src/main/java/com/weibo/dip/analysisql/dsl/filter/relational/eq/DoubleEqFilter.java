package com.weibo.dip.analysisql.dsl.filter.relational.eq;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** DoubleEqFilter. */
public class DoubleEqFilter extends EqFilter {
  private double value;

  public DoubleEqFilter() {}

  /**
   * Initializes a instance with name and double value.
   *
   * @param name name
   * @param value double value
   */
  public DoubleEqFilter(String name, double value) {
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
