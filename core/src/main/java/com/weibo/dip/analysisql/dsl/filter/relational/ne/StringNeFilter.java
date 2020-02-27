package com.weibo.dip.analysisql.dsl.filter.relational.ne;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** StringNeRelationalFilter. */
public class StringNeFilter extends NeFilter<String> {
  public StringNeFilter() {}

  /**
   * Initializes a instance with name and string value.
   *
   * @param name name
   * @param value string value
   */
  public StringNeFilter(String name, String value) {
    super(name, RelationalFilter.STRING, value);
  }
}
