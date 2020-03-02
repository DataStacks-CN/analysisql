package com.weibo.dip.analysisql.dsl.filter.relational.le;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LeFilter. */
public abstract class LeFilter<T> extends RelationalFilter<T> {
  public LeFilter() {}

  /**
   * Initialize a instance with name, type and value.
   *
   * @param name name
   * @param type type
   * @param value value
   */
  public LeFilter(String name, String type, T value) {
    super(Filter.LE, name, type, value);
  }
}
