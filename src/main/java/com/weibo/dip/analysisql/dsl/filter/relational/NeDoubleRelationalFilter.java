package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class NeDoubleRelationalFilter extends DoubleRelationalFilter {
  public NeDoubleRelationalFilter(String name, double value) {
    super(Filter.NE, name, value);
  }
}
