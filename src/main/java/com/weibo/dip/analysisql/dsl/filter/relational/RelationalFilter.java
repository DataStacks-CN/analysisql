package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** RelationalFilter. */
public abstract class RelationalFilter extends Filter {
  protected String name;

  public RelationalFilter() {}

  /**
   * Initializes a instance with operator and name.
   *
   * @param operator operator
   * @param name name
   */
  public RelationalFilter(String operator, String name) {
    super(operator);

    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
