package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** EqDoubleRelationalFilter. */
public class EqDoubleRelationalFilter extends DoubleRelationalFilter {
  public EqDoubleRelationalFilter() {}

  public EqDoubleRelationalFilter(String name, double value) {
    super(Filter.EQ, name, value);
  }
}
