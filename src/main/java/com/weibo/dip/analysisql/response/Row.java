package com.weibo.dip.analysisql.response;

import com.google.gson.GsonBuilder;
import com.weibo.dip.analysisql.response.column.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Row. */
public class Row implements Serializable {
  private List<Column<?>> columns;

  public Row() {}

  /**
   * Initializes a instance with columns.
   *
   * @param columns columns
   */
  public Row(List<Column<?>> columns) {
    this.columns = columns;
  }

  public List<Column<?>> getColumns() {
    return columns;
  }

  public void setColumns(List<Column<?>> columns) {
    this.columns = columns;
  }

  /**
   * Add a column.
   *
   * @param column column
   */
  public void add(Column<?> column) {
    if (Objects.isNull(columns)) {
      columns = new ArrayList<>();
    }

    columns.add(column);
  }

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}
