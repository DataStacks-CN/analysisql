package com.weibo.dip.analysisql.dsl.filter.relational;

/** @author yurun */
public abstract class StringRelationalFilter extends RelationalFilter {
  protected String value;

  public StringRelationalFilter() {}

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
