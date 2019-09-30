package com.weibo.dip.analysisql.response;

import com.weibo.dip.analysisql.response.column.Column;
import java.io.Serializable;
import java.util.List;

/** Row. */
public class Row implements Serializable {
  private List<Column<?>> columns;

  public List<Column<?>> getColumns() {
    return columns;
  }

  public void setColumns(List<Column<?>> columns) {
    this.columns = columns;
  }
}
