package com.weibo.dip.analysisql.dsl.filter.relational.ne;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LongNeRelationalFilter. */
public class LongNeFilter extends NeFilter {
  private long value;

  public LongNeFilter() {}

  /**
   * Initializes a instance with name and long value.
   *
   * @param name name
   * @param value long value
   */
  public LongNeFilter(String name, long value) {
    super(name, RelationalFilter.LONG);

    this.value = value;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }
}
