package com.weibo.dip.analysisql.dsl.filter.relational.in;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** StringInFilter. */
public class StringInFilter extends InFilter {
  private String[] values;

  public StringInFilter() {}

  /**
   * Initializes a instance with name and string array.
   *
   * @param name name
   * @param values string array
   */
  public StringInFilter(String name, String[] values) {
    super(name, RelationalFilter.STRING);

    this.values = values;
  }

  public String[] getValues() {
    return values;
  }

  public void setValues(String[] values) {
    this.values = values;
  }
}
