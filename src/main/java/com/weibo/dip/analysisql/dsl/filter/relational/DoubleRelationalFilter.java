package com.weibo.dip.analysisql.dsl.filter.relational;

/** DoubleRelationalFilter. */
public abstract class DoubleRelationalFilter extends RelationalFilter {
  protected double value;

  public DoubleRelationalFilter() {}

  /**
   * Initializes a instance with operato, name and double value.
   *
   * @param operator operator
   * @param name name
   * @param value value
   */
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
