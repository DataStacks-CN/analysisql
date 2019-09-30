package com.weibo.dip.analysisql.dsl.filter.relational.ge;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** StringGeFilter. */
public class StringGeFilter extends GeFilter {
  private String value;

  public StringGeFilter() {}

  /**
   * Initializes a instance with name and string value.
   *
   * @param name name
   * @param value string value
   */
  public StringGeFilter(String name, String value) {
    super(name, RelationalFilter.STRING);

    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
