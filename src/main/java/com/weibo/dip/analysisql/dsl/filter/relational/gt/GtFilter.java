package com.weibo.dip.analysisql.dsl.filter.relational.gt;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** GtRelationalFilter. */
public abstract class GtFilter extends RelationalFilter {
  public GtFilter() {}

  /**
   * Initializes a instance with name.
   *
   * @param name name
   * @param type type
   */
  public GtFilter(String name, String type) {
    super(Filter.GT, name, type);
  }
}
