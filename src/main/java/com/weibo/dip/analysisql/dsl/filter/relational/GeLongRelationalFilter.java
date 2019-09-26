package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class GeLongRelationalFilter extends LongRelationalFilter {
  public GeLongRelationalFilter(String name, long value) {
    super(Filter.GE, name, value);
  }
}
