package com.weibo.dip.analysisql.dsl.filter.relational.ge;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** DoubleGeFilter. */
public class DoubleGeFilter extends GeFilter<Double> {
  public DoubleGeFilter() {}

  /**
   * Initialize a instance with name and double value.
   *
   * @param name name
   * @param value double value
   */
  public DoubleGeFilter(String name, double value) {
    super(name, RelationalFilter.DOUBLE, value);
  }
}
