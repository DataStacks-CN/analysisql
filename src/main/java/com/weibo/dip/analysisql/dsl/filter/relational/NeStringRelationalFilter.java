package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class NeStringRelationalFilter extends StringRelationalFilter {
  public NeStringRelationalFilter() {}

  public NeStringRelationalFilter(String name, String value) {
    super(Filter.NE, name, value);
  }
}
