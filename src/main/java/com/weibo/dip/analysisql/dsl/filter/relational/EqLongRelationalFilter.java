package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class EqLongRelationalFilter extends LongRelationalFilter {
  public EqLongRelationalFilter(String name, long value) {
    super(Filter.EQ, name, value);
  }
}
