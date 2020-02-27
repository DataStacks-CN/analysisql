package com.weibo.dip.analysisql.dsl.filter.relational.eq;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** StringEqFilter. */
public class StringEqFilter extends EqFilter<String> {
  public StringEqFilter() {}

  /**
   * Initializes a instance with name and string value.
   *
   * @param name name
   * @param value string value
   */
  public StringEqFilter(String name, String value) {
    super(name, RelationalFilter.STRING, value);
  }
}
