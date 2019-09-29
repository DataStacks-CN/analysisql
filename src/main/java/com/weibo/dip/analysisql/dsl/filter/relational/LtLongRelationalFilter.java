package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** LtLongRelationalFilter. */
public class LtLongRelationalFilter extends LongRelationalFilter {
  public LtLongRelationalFilter() {}

  public LtLongRelationalFilter(String name, long value) {
    super(Filter.LT, name, value);
  }
}
