package com.weibo.dip.analysisql.dsl.filter.relational;

/** StringRelationalFilter. */
public abstract class StringRelationalFilter extends RelationalFilter {
  protected String value;

  public StringRelationalFilter() {}

  /**
   * Initializes a instance with operator, name and value.
   *
   * @param operator operator
   * @param name name
   * @param value value
   */
  public StringRelationalFilter(String operator, String name, String value) {
    super(operator, name);

    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
