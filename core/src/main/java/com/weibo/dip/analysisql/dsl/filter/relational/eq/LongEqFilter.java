package com.weibo.dip.analysisql.dsl.filter.relational.eq;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LongEqFilter. */
public class LongEqFilter extends EqFilter<Long> {
  public LongEqFilter() {}

  /**
   * Initialize a instance with name and long value.
   *
   * @param name name
   * @param value long value
   */
  public LongEqFilter(String name, long value) {
    super(name, RelationalFilter.LONG, value);
  }
}
