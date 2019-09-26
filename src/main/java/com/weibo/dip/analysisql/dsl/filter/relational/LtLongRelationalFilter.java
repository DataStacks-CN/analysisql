package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class LtLongRelationalFilter extends LongRelationalFilter {
  public LtLongRelationalFilter(String name, long value) {
    super(Filter.LT, name, value);
  }
}
