package com.weibo.dip.analysisql.dsl.filter.relational.ne;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** NeRelationalFilter. */
public abstract class NeFilter<T> extends RelationalFilter<T> {
  public NeFilter() {}

  /**
   * Initializes a instance with name, type and value.
   *
   * @param name name
   * @param type type
   * @param value value
   */
  public NeFilter(String name, String type, T value) {
    super(Filter.NE, name, type, value);
  }
}
