package com.weibo.dip.analysisql.dsl.request;

import com.weibo.dip.analysisql.GsonCreator;
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
