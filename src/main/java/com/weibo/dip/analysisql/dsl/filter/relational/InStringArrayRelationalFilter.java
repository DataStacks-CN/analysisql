package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** InStringArrayRelationalFilter. */
public class InStringArrayRelationalFilter extends RelationalFilter {
  private String[] values;

  public InStringArrayRelationalFilter() {}

  /**
   * Initializes a instance with name and string array.
   *
   * @param name string
   * @param values string array
   */
  public InStringArrayRelationalFilter(String name, String[] values) {
    super(Filter.IN, name);

    this.values = values;
  }

  public String[] getValues() {
    return values;
  }

  public void setValues(String[] values) {
    this.values = values;
  }
}
