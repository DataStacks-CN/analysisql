package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class LtDoubleRelationalFilter extends DoubleRelationalFilter {
  public LtDoubleRelationalFilter() {}

  public LtDoubleRelationalFilter(String name, double value) {
    super(Filter.LT, name, value);
  }
}
