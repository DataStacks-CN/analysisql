package com.weibo.dip.analysisql.dsl.filter.relational.le;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LeFilter. */
public abstract class LeFilter extends RelationalFilter {
  public LeFilter() {}

  /**
   * Initializes a instance with name.
   *
   * @param name name
   * @param type type
   */
  public LeFilter(String name, String type) {
    super(Filter.LE, name, type);
  }
}
