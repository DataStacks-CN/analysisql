package com.weibo.dip.analysisql.dsl.filter.relational.eq;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** DoubleEqFilter. */
public class DoubleEqFilter extends EqFilter<Double> {
  public DoubleEqFilter() {}

  /**
   * Initializes a instance with name and double value.
   *
   * @param name name
   * @param value double value
   */
  public DoubleEqFilter(String name, double value) {
    super(name, RelationalFilter.DOUBLE, value);
  }
}
