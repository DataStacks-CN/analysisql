package com.weibo.dip.analysisql.dsl.filter.relational;

/** @author yurun */
public abstract class DoubleRelationalFilter extends RelationalFilter {
  protected double value;

  public DoubleRelationalFilter() {}

  public DoubleRelationalFilter(String operator, String name, double value) {
    super(operator, name);

    this.value = value;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
