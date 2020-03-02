package com.weibo.dip.analysisql.dsl.filter.relational.le;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** DoubleLeFilter. */
public class DoubleLeFilter extends LeFilter<Double> {
  public DoubleLeFilter() {}

  /**
   * Initialize a instance with name and double value.
   *
   * @param name name
   * @param value double value
   */
  public DoubleLeFilter(String name, double value) {
    super(name, RelationalFilter.DOUBLE, value);
  }
}
