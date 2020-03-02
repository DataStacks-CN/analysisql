package com.weibo.dip.analysisql.dsl.filter.relational.ne;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LongNeRelationalFilter. */
public class LongNeFilter extends NeFilter<Long> {
  public LongNeFilter() {}

  /**
   * Initialize a instance with name and long value.
   *
   * @param name name
   * @param value long value
   */
  public LongNeFilter(String name, long value) {
    super(name, RelationalFilter.LONG, value);
  }
}
