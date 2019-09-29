package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** InDoubleArrayRelationalFilter. */
public class InDoubleArrayRelationalFilter extends RelationalFilter {
  private double[] values;

  public InDoubleArrayRelationalFilter() {}

  /**
   * Initializes a instance with name and double array.
   *
   * @param name name
   * @param values double array
   */
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
