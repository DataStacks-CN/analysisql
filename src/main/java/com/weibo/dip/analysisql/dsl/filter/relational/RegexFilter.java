package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** RegexFilter. */
public class RegexFilter extends RelationalFilter {
  private String pattern;

  public RegexFilter() {}

  /**
   * Initializes a instance with name and pattern.
   *
   * @param name name
   * @param pattern pattern
   */
  public RegexFilter(String name, String pattern) {
    super(Filter.REGEX, name, RelationalFilter.STRING);

    this.pattern = pattern;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }
}
