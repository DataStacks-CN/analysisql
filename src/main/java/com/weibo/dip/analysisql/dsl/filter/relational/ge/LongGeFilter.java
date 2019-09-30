package com.weibo.dip.analysisql.dsl.filter.relational.ge;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LongGeFilter. */
public class LongGeFilter extends GeFilter {
  private long value;

  public LongGeFilter() {}

  /**
   * Initializes a instance with name and long value.
   *
   * @param name name
   * @param value long value
   */
  public LongGeFilter(String name, long value) {
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
