package com.weibo.dip.analysisql.dsl.request;

import java.io.Serializable;

/** @author yurun */
public class Interval implements Serializable {
  private String start;
  private String end;

  public Interval() {}

  public Interval(String start, String end) {
    this.start = start;
    this.end = end;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }
}
