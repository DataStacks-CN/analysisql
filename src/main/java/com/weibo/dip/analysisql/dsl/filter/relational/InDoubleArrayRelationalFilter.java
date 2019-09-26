package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class InDoubleArrayRelationalFilter extends RelationalFilter {
  private double[] values;

  public InDoubleArrayRelationalFilter() {}

  public InDoubleArrayRelationalFilter(String name, double[] values) {
    super(Filter.IN, name);

    this.values = values;
  }

  public double[] getValues() {
    return values;
  }

  public void setValues(double[] values) {
    this.values = values;
  }
}
