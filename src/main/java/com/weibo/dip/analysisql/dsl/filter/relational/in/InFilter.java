package com.weibo.dip.analysisql.dsl.filter.relational.in;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** InFilter. */
public abstract class InFilter extends RelationalFilter {
  public InFilter() {}

  /**
   * Initializes a instance with name.
   *
   * @param name name
   * @param type type
   */
  public InFilter(String name, String type) {
    super(Filter.IN, name, type);
  }
}
