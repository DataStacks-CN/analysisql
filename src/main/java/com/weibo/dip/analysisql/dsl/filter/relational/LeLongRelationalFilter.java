package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** LeLongRelationalFilter. */
public class LeLongRelationalFilter extends LongRelationalFilter {
  public LeLongRelationalFilter() {}

  public LeLongRelationalFilter(String name, long value) {
    super(Filter.LE, name, value);
  }
}
