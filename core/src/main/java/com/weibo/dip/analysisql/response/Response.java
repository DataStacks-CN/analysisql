package com.weibo.dip.analysisql.response;

import com.weibo.dip.analysisql.util.GsonCreator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Response. */
public class Response implements Serializable {
  private String sessionId;
  private int code;
  private String msg;
  private List<Row> rows;

  public Response() {}

  /**
   * Initialize a instance with code, msg and rows.
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

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
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

  /**
   * Add a row.
   *
   * @param row row
   */
  public void add(Row row) {
    if (Objects.isNull(rows)) {
      rows = new ArrayList<>();
    }

    rows.add(row);
  }

  @Override
  public String toString() {
    return GsonCreator.create().toJson(this);
  }
}
