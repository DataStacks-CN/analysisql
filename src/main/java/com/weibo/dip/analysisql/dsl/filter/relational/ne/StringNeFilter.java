package com.weibo.dip.analysisql.dsl.filter.relational.ne;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** StringNeRelationalFilter. */
public class StringNeFilter extends NeFilter {
  private String value;

  public StringNeFilter() {}

  /**
   * Initializes a instance with name and string value.
   *
   * @param name name
   * @param value string value
   */
  public StringNeFilter(String name, String value) {
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
