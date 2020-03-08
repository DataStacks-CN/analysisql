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
    long milliseconds;

    switch (unit) {
      case s:
        milliseconds = data * 1000;
        break;
      case m:
        milliseconds = data * 60 * 1000;
        break;
      case h:
        milliseconds = data * 3600 * 1000;
        break;
      case d:
        milliseconds = data * 24 * 3600 * 1000;
        break;
      case w:
        milliseconds = data * 7 * 24 * 3600 * 1000;
        break;
      case M:
        milliseconds = data * 30 * 24 * 3600 * 1000;
        break;
      case q:
        milliseconds = data * 120 * 24 * 3600 * 1000;
        break;
      case y:
        milliseconds = data * 360 * 24 * 3600 * 1000;
        break;
      default:
        throw new UnsupportedOperationException();
    }

    if (milliseconds <= 0) {
      milliseconds = Long.MAX_VALUE;
    }

    return milliseconds;
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
