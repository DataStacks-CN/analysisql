package com.weibo.dip.analysisql.dsl.filter;

/** RegexFilter. */
public class RegexFilter extends Filter {
  private String name;
  private String pattern;

  public RegexFilter() {}

  /**
   * Initializes a instance with name and pattern.
   *
   * @param name name
   * @param pattern pattern
   */
  public RegexFilter(String name, String pattern) {
    super(Filter.REGEX);

    this.name = name;
    this.pattern = pattern;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }
}
