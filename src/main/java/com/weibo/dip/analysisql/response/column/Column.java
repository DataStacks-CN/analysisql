package com.weibo.dip.analysisql.response.column;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;
import java.io.Serializable;

/** Column. */
public abstract class Column<T> implements Serializable {
  public static final String STRING = RelationalFilter.STRING;
  public static final String LONG = RelationalFilter.LONG;
  public static final String DOUBLE = RelationalFilter.DOUBLE;

  protected String name;
  protected String type;
  protected T value;

  public Column() {}

  public Column(String name, String type, T value) {
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
