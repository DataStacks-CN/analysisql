package com.weibo.dip.analysisql.dsl.filter.relational;

/** LongRelationalFilter. */
public abstract class LongRelationalFilter extends RelationalFilter {
  protected long value;

  public LongRelationalFilter() {}

  /**
   * Initializes a instance with operator, name and long value.
   *
   * @param operator operator
   * @param name name
   * @param value value
   */
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
