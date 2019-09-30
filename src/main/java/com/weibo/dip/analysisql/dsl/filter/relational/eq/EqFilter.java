package com.weibo.dip.analysisql.dsl.filter.relational.eq;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** EqFilter. */
public abstract class EqFilter extends RelationalFilter {
  public EqFilter() {}

  /**
   * Initializes a instance with name.
   *
   * @param name name
   * @param type type
   */
  public EqFilter(String name, String type) {
    super(Filter.EQ, name, type);
  }
}
