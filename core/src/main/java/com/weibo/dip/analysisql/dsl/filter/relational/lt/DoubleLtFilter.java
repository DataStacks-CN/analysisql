package com.weibo.dip.analysisql.dsl.filter.relational.lt;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** DoubleLtFilter. */
public class DoubleLtFilter extends LtFilter<Double> {
  public DoubleLtFilter() {}

  /**
   * Initialize a instance with name and double value.
   *
   * @param name name
   * @param value double value
   */
  public DoubleLtFilter(String name, double value) {
    super(name, RelationalFilter.DOUBLE, value);
  }
}
