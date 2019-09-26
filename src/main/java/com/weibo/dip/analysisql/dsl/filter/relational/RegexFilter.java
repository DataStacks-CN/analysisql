package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class RegexFilter extends RelationalFilter {
  private String pattern;

  public RegexFilter(String name, String pattern) {
    super(Filter.REGEX, name);

    this.pattern = pattern;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }
}
