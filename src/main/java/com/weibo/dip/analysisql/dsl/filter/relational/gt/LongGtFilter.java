package com.weibo.dip.analysisql.dsl.filter.relational.gt;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LongGtRelationalFilter. */
public class LongGtFilter extends GtFilter {
  private long value;

  public LongGtFilter() {}

  /**
   * Initializes a instance with name and long value.
   *
   * @param name name
   * @param value long value
   */
  public LongGtFilter(String name, long value) {
    super(name, RelationalFilter.STRING);

    this.value = value;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }
}
