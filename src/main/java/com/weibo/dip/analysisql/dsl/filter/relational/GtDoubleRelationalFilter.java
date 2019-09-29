package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** GtDoubleRelationalFilter. */
public class GtDoubleRelationalFilter extends DoubleRelationalFilter {
  public GtDoubleRelationalFilter() {}

  public GtDoubleRelationalFilter(String name, double value) {
    super(Filter.GT, name, value);
  }
}
