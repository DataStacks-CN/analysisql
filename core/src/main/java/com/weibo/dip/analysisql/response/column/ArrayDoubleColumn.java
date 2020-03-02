package com.weibo.dip.analysisql.response.column;

/** DoubleArrayColumn. */
public class ArrayDoubleColumn extends Column<double[]> {
  public ArrayDoubleColumn() {}

  /**
   * Initialize a instance with name and double array.
   *
   * @param name name
   * @param values long array
   */
  public ArrayDoubleColumn(String name, double[] values) {
    super(name, Column.ARRAY_DOUBLE, values);
  }
}
