package com.weibo.dip.analysisql.response.column;

/** StringColumn. */
public class StringColumn extends Column<String> {
  public StringColumn() {}

  public StringColumn(String name, String value) {
    super(name, Column.STRING, value);
  }
}
