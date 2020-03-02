package com.weibo.dip.analysisql.response.column;

/** StringColumn. */
public class StringColumn extends Column<String> {
  public StringColumn() {}

  /**
   * Initialize a instance with name and string value.
   *
   * @param name name
   * @param value string value
   */
  public StringColumn(String name, String value) {
    super(name, Column.STRING, value);
  }
}
