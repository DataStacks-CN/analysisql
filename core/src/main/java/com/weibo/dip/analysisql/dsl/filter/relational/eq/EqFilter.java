package com.weibo.dip.analysisql.dsl.filter.relational.eq;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** EqFilter. */
public abstract class EqFilter<T> extends RelationalFilter<T> {
  public EqFilter() {}

  /**
   * Initialize a instance with name, type and value.
   *
   * @param name name
   * @param type type
   * @param value value
   */
  public EqFilter(String name, String type, T value) {
    super(Filter.EQ, name, type, value);
  }
}
