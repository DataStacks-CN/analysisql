package com.weibo.dip.analysisql.dsl.filter.in;

/** LongInFilter. */
public class LongInFilter extends InFilter<Long> {
  public LongInFilter() {}

  /**
   * Initialize a instance with name and long array.
   *
   * @param name name
   * @param values long array
   */
  public LongInFilter(String name, Long[] values) {
    super(name, InFilter.LONG, values);
  }
}
