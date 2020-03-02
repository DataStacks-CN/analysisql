package com.weibo.dip.analysisql.response.column;

import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;
import com.weibo.dip.analysisql.util.GsonCreator;
import java.io.Serializable;

/** Column. */
public abstract class Column<T> implements Serializable {
  public static final String STRING = RelationalFilter.STRING;
  public static final String LONG = RelationalFilter.LONG;
  public static final String DOUBLE = RelationalFilter.DOUBLE;

  public static final String ARRAY_STRING = "array(string)";
  public static final String ARRAY_LONG = "array(long)";
  public static final String ARRAY_DOUBLE = "array(double)";

  protected String name;
  protected String type;
  protected T value;

  public Column() {}

  /**
   * Initialize a instance with name, type, value.
   *
   * @param name name
   * @param type type
   * @param value value
   */
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

  @Override
  public String toString() {
    return GsonCreator.create().toJson(this);
  }
}
