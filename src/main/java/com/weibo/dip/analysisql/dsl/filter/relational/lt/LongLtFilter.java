package com.weibo.dip.analysisql.dsl.filter.relational.lt;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LongLtFilter. */
public class LongLtFilter extends LtFilter {
  private long value;

  public LongLtFilter() {}

  /**
   * Initializes a instance with name and long value.
   *
   * @param name name
   * @param value long value
   */
  public LongLtFilter(String name, long value) {
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
