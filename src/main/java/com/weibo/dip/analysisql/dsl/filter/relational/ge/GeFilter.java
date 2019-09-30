package com.weibo.dip.analysisql.dsl.filter.relational.ge;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** GeFilter. */
public abstract class GeFilter extends RelationalFilter {
  public GeFilter() {}

  /**
   * Initializes a instance with name.
   *
   * @param name name
   * @param type type
   */
  public GeFilter(String name, String type) {
    super(Filter.GE, name, type);
  }
}
