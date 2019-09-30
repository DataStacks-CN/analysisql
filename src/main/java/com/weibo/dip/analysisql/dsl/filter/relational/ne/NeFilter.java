package com.weibo.dip.analysisql.dsl.filter.relational.ne;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** NeRelationalFilter. */
public abstract class NeFilter extends RelationalFilter {
  public NeFilter() {}

  /**
   * Initializes a instance with name.
   *
   * @param name name
   * @param type type
   */
  public NeFilter(String name, String type) {
    super(Filter.NE, name, type);
  }
}
