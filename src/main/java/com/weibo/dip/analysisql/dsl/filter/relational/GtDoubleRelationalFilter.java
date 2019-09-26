package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class GtDoubleRelationalFilter extends DoubleRelationalFilter {
  public GtDoubleRelationalFilter(String name, double value) {
    super(Filter.GT, name, value);
  }
}
