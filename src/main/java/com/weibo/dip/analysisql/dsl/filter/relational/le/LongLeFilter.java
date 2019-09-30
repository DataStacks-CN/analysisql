package com.weibo.dip.analysisql.dsl.filter.relational.le;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LongLeFilter. */
public class LongLeFilter extends LeFilter {
  private long value;

  public LongLeFilter() {}

  /**
   * Initializes a instance with name and long value.
   *
   * @param name name
   * @param value long value
   */
  public LongLeFilter(String name, long value) {
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
