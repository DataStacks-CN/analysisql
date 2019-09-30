package com.weibo.dip.analysisql.dsl.filter.relational.in;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** DoubleInFilter. */
public class DoubleInFilter extends InFilter {
  private double[] values;

  public DoubleInFilter() {}

  /**
   * Initializes a instance with name and double array.
   *
   * @param name name
   * @param values double array
   */
  public DoubleInFilter(String name, double[] values) {
    super(name, RelationalFilter.DOUBLE);

    this.values = values;
  }

  public double[] getValues() {
    return values;
  }

  public void setValues(double[] values) {
    this.values = values;
  }
}
