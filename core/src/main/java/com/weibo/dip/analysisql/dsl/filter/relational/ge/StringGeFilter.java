package com.weibo.dip.analysisql.dsl.filter.relational.ge;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** StringGeFilter. */
public class StringGeFilter extends GeFilter<String> {
  public StringGeFilter() {}

  /**
   * Initialize a instance with name and string value.
   *
   * @param name name
   * @param value string value
   */
  public StringGeFilter(String name, String value) {
    super(name, RelationalFilter.STRING, value);
  }
}
