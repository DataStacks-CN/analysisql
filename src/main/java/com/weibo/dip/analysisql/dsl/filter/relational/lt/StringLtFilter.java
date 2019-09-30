package com.weibo.dip.analysisql.dsl.filter.relational.lt;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** StringLtFilter. */
public class StringLtFilter extends LtFilter {
  private String value;

  public StringLtFilter() {}

  /**
   * Initializes a instance with name and string value.
   *
   * @param name name
   * @param value string value
   */
  public StringLtFilter(String name, String value) {
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
