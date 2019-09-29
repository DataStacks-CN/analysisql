package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** InLongArrayRelationalFilter. */
public class InLongArrayRelationalFilter extends RelationalFilter {
  private long[] values;

  public InLongArrayRelationalFilter() {}

  /**
   * Initializes a instance with name and long array.
   *
   * @param name name
   * @param values long array
   */
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
