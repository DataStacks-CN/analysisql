package com.weibo.dip.analysisql.dsl.filter.relational.gt;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** GtRelationalFilter. */
public abstract class GtFilter<T> extends RelationalFilter<T> {
  public GtFilter() {}

  /**
   * Initializes a instance with name, type and value.
   *
   * @param name name
   * @param type type
   * @param value value
   */
  public GtFilter(String name, String type, T value) {
    super(Filter.GT, name, type, value);
  }
}
