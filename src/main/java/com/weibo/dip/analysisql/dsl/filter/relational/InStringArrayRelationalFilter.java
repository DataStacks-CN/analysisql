package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class InStringArrayRelationalFilter extends RelationalFilter {
  private String[] values;

  public InStringArrayRelationalFilter() {}

  public InStringArrayRelationalFilter(String name, String[] values) {
    super(Filter.IN, name);

    this.values = values;
  }

  public String[] getValues() {
    return values;
  }

  public void setValues(String[] values) {
    this.values = values;
  }
}
