package com.weibo.dip.analysisql.dsl.filter.relational.ge;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** GeFilter. */
public abstract class GeFilter<T> extends RelationalFilter<T> {
  public GeFilter() {}

  /**
   * Initialize a instance with name, type and value.
   *
   * @param name name
   * @param type type
   * @param value value
   */
  public GeFilter(String name, String type, T value) {
    super(Filter.GE, name, type, value);
  }
}
