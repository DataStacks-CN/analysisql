package com.weibo.dip.analysisql.dsl.filter.in;

/** StringInFilter. */
public class StringInFilter extends InFilter<String> {
  public StringInFilter() {}

  /**
   * Initializes a instance with name and string array.
   *
   * @param name name
   * @param values string array
   */
  public StringInFilter(String name, String[] values) {
    super(name, InFilter.STRING, values);
  }
}
