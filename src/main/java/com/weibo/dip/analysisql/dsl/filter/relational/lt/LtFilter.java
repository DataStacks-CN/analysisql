package com.weibo.dip.analysisql.dsl.filter.relational.lt;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LtFilter. */
public abstract class LtFilter extends RelationalFilter {
  public LtFilter() {}

  /**
   * Initializes a instance with name.
   *
   * @param name name
   * @param type type
   */
  public LtFilter(String name, String type) {
    super(Filter.LT, name, type);
  }
}
