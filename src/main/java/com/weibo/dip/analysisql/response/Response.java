package com.weibo.dip.analysisql.response;

import java.io.Serializable;
import java.util.List;

/** Response. */
public class Response implements Serializable {
  private int code;
  private String msg;
  private List<Row> rows;

  public Response() {}

  /**
   * Initializes a instance with code, msg and rows.
   *
   * @param code code
   * @param msg msg
   * @param rows rows
   */
  public Response(int code, String msg, List<Row> rows) {
    this.code = code;
    this.msg = msg;
    this.rows = rows;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public List<Row> getRows() {
    return rows;
  }

  public void setRows(List<Row> rows) {
    this.rows = rows;
  }
}
