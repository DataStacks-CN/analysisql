package com.weibo.dip.analysisql.dsl.filter.in;

/** LongInFilter. */
public class DoubleInFilter extends InFilter<Double> {
  public DoubleInFilter() {}

  /**
   * Initialize a instance with name and double array.
   *
   * @param name name
   * @param values double array
   */
  public DoubleInFilter(String name, Double[] values) {
    super(name, InFilter.DOUBLE, values);
  }
}
