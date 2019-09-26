package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class InLongArrayRelationalFilter extends RelationalFilter {
  private long[] values;

  public InLongArrayRelationalFilter() {}

  public InLongArrayRelationalFilter(String name, long[] values) {
    super(Filter.IN, name);

    this.values = values;
  }

  public long[] getValues() {
    return values;
  }

  public void setValues(long[] values) {
    this.values = values;
  }
}
