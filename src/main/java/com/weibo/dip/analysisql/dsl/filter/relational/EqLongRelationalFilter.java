package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** EqLongRelationalFilter. */
public class EqLongRelationalFilter extends LongRelationalFilter {
  public EqLongRelationalFilter() {}

  public EqLongRelationalFilter(String name, long value) {
    super(Filter.EQ, name, value);
  }
}
