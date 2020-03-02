package com.weibo.dip.analysisql.dsl.filter.relational.lt;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LongLtFilter. */
public class LongLtFilter extends LtFilter<Long> {
  public LongLtFilter() {}

  /**
   * Initialize a instance with name and long value.
   *
   * @param name name
   * @param value long value
   */
  public LongLtFilter(String name, long value) {
    super(name, RelationalFilter.LONG, value);
  }
}
