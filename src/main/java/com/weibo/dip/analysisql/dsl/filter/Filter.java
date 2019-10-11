package com.weibo.dip.analysisql.dsl.filter;

import com.weibo.dip.analysisql.util.GsonCreator;
import java.io.Serializable;

/** Filter. */
public abstract class Filter implements Serializable {
  // logical
  public static final String AND = "and";
  public static final String OR = "or";
  public static final String NOT = "not";

  // relational
  public static final String EQ = "eq";
  public static final String NE = "ne";
  public static final String GT = "gt";
  public static final String LT = "lt";
  public static final String GE = "ge";
  public static final String LE = "le";

  public static final String IN = "in";

  public static final String REGEX = "regex";

  protected String operator;

  public Filter() {}

  public Filter(String operator) {
    this.operator = operator;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  @Override
  public String toString() {
    return GsonCreator.create().toJson(this);
  }
}
