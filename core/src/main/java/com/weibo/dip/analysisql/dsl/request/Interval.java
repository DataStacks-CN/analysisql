package com.weibo.dip.analysisql.dsl.request;

import com.weibo.dip.analysisql.util.GsonCreator;
import java.io.Serializable;
import java.util.Date;

/** Interval. */
public class Interval implements Serializable {
  private Date start;
  private Date end;

  public Interval() {}

  public Interval(Date start, Date end) {
    this.start = start;
    this.end = end;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }

  @Override
  public String toString() {
    return GsonCreator.create().toJson(this);
  }
}
