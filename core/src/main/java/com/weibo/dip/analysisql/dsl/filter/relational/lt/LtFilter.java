package com.weibo.dip.analysisql.dsl.filter.relational.lt;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** LtFilter. */
public abstract class LtFilter<T> extends RelationalFilter<T> {
  public LtFilter() {}

  /**
   * Initializes a instance with name, type and value.
   *
   * @param name name
   * @param type type
   * @param value value
   */
  public LtFilter(String name, String type, T value) {
    super(Filter.LT, name, type, value);
  }
}
