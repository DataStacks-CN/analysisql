package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class LtStringRelationalFilter extends StringRelationalFilter {
  public LtStringRelationalFilter(String name, String value) {
    super(Filter.LT, name, value);
  }
}
