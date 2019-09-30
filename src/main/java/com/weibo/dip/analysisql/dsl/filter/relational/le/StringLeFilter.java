package com.weibo.dip.analysisql.dsl.filter.relational.le;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** StringLeFilter. */
public class StringLeFilter extends LeFilter<String> {
  public StringLeFilter() {}

  /**
   * Initializes a instance with name and string value.
   *
   * @param name name
   * @param value string value
   */
  public StringLeFilter(String name, String value) {
    super(name, RelationalFilter.STRING, value);
  }
}
