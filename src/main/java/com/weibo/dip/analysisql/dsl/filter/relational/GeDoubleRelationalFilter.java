package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** GeDoubleRelationalFilter. */
public class GeDoubleRelationalFilter extends DoubleRelationalFilter {
  public GeDoubleRelationalFilter() {}

  public GeDoubleRelationalFilter(String name, double value) {
    super(Filter.GE, name, value);
  }
}
