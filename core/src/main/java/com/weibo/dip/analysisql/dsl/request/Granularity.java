package com.weibo.dip.analysisql.dsl.request;

import com.weibo.dip.analysisql.util.GsonCreator;
import java.io.Serializable;

/** Granularity. */
public class Granularity implements Serializable {
  private int data;
  private Unit unit;

  public Granularity() {}

  public Granularity(int data, Unit unit) {
    this.data = data;
    this.unit = unit;
  }

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  /**
   * Get milliseconds.
   *
   * @return milliseconds
   */
  public long getMilliseconds() {
    switch (unit) {
      case s:
        return data * 1000;
      case m:
        return data * 60 * 1000;
      case h:
        return data * 3600 * 1000;
      case d:
        return data * 24 * 3600 * 1000;
      case w:
        return data * 7 * 24 * 3600 * 1000;
      case M:
        return data * 30 * 24 * 3600 * 1000;
      case q:
        return data * 120 * 24 * 3600 * 1000;
      case y:
        return data * 360 * 24 * 3600 * 1000;
      default:
        throw new UnsupportedOperationException();
    }
  }

  public enum Unit {
    s,
    m,
    h,
    d,
    w,
    M,
    q,
    y
  }

  @Override
  public String toString() {
    return GsonCreator.create().toJson(this);
  }
}
