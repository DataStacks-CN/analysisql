package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class LeDoubleRelationalFilter extends DoubleRelationalFilter {
  public LeDoubleRelationalFilter() {}

  public LeDoubleRelationalFilter(String name, double value) {
    super(Filter.LE, name, value);
  }
}
