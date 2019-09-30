package com.weibo.dip.analysisql.dsl.filter.relational.lt;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** StringLtFilter. */
public class StringLtFilter extends LtFilter<String> {
  public StringLtFilter() {}

  /**
   * Initializes a instance with name and string value.
   *
   * @param name name
   * @param value string value
   */
  public StringLtFilter(String name, String value) {
    super(name, RelationalFilter.STRING, value);
  }
}
