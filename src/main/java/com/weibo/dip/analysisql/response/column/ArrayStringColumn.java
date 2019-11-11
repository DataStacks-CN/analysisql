package com.weibo.dip.analysisql.response.column;

/** ArrayStringColumn. */
public class ArrayStringColumn extends Column<String[]> {
  public ArrayStringColumn() {}

  /**
   * Initializes a instance with name and string array.
   *
   * @param name name
   * @param values string array
   */
  public ArrayStringColumn(String name, String[] values) {
    super(name, Column.ARRAY_STRING, values);
  }
}
