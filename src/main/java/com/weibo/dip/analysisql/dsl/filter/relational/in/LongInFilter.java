package com.weibo.dip.analysisql.dsl.filter.relational.in;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LongInFilter. */
public class LongInFilter extends InFilter {
  private long[] values;

  public LongInFilter() {}

  /**
   * Initializes a instance with name and long array.
   *
   * @param name name
   * @param values long array
   */
  public LongInFilter(String name, long[] values) {
    super(name, RelationalFilter.LONG);

    this.values = values;
  }

  public long[] getValues() {
    return values;
  }

  public void setValues(long[] values) {
    this.values = values;
  }
}
