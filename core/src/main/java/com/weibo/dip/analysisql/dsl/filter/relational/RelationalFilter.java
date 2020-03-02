package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** RelationalFilter. */
public abstract class RelationalFilter<T> extends Filter {
  public static final String STRING = "string";
  public static final String LONG = "long";
  public static final String DOUBLE = "double";

  protected String name;
  protected String type;
  protected T value;

  public RelationalFilter() {}

  /**
   * Initialize a instance with operator, name and type.
   *
   * @param operator operator
   * @param name name
   * @param type type
   */
  public RelationalFilter(String operator, String name, String type, T value) {
    super(operator);

    this.name = name;
    this.type = type;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }
}
