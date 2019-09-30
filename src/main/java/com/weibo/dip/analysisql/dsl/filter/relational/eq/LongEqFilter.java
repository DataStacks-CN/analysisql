package com.weibo.dip.analysisql.dsl.filter.relational.eq;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LongEqFilter. */
public class LongEqFilter extends EqFilter {
  private long value;

  public LongEqFilter() {}

  /**
   * Initializes a instance with name and long value.
   *
   * @param name name
   * @param value long value
   */
  public LongEqFilter(String name, long value) {
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
