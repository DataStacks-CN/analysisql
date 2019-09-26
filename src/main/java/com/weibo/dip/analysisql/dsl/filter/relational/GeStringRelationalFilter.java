package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class GeStringRelationalFilter extends StringRelationalFilter {
  public GeStringRelationalFilter() {}

  public GeStringRelationalFilter(String name, String value) {
    super(Filter.GE, name, value);
  }
}
