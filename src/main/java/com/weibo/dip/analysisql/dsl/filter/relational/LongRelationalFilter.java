package com.weibo.dip.analysisql.dsl.filter.relational;

/** @author yurun */
public abstract class LongRelationalFilter extends RelationalFilter {
  protected long value;

  public LongRelationalFilter() {}

  public LongRelationalFilter(String operator, String name, long value) {
    super(operator, name);

    this.value = value;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }
}
