package com.weibo.dip.analysisql.dsl.filter.relational.gt;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LongGtRelationalFilter. */
public class LongGtFilter extends GtFilter<Long> {
  public LongGtFilter() {}

  /**
   * Initialize a instance with name and long value.
   *
   * @param name name
   * @param value long value
   */
  public LongGtFilter(String name, long value) {
    super(name, RelationalFilter.LONG, value);
  }
}
