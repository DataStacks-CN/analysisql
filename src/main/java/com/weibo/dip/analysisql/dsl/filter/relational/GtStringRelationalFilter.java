package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class GtStringRelationalFilter extends StringRelationalFilter {
  public GtStringRelationalFilter(String name, String value) {
    super(Filter.GT, name, value);
  }
}
