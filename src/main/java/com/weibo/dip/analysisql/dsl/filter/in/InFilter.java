package com.weibo.dip.analysisql.dsl.filter.in;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;

/** InFilter. */
public abstract class InFilter<T> extends Filter {
  public static final String STRING = RelationalFilter.STRING;
  public static final String LONG = RelationalFilter.LONG;
  public static final String DOUBLE = RelationalFilter.DOUBLE;

  protected String type;
  protected T[] values;

  public InFilter() {}

  /**
   * Initializes a instance with name and array.
   *
   * @param name name
   * @param values array
   */
  public InFilter(String name, String type, T[] values) {
    super(Filter.IN);

    this.type = type;
    this.values = values;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public T[] getValues() {
    return values;
  }

  public void setValues(T[] values) {
    this.values = values;
  }
}
