package com.weibo.dip.analysisql.dsl.request;

import com.weibo.dip.analysisql.GsonCreator;
import java.io.Serializable;

/** Order. */
public class Order implements Serializable {
  private String name;
  private Sort sort;

  public Order() {}

  public Order(String name, Sort sort) {
    this.name = name;
    this.sort = sort;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Sort getSort() {
    return sort;
  }

  public void setSort(Sort sort) {
    this.sort = sort;
  }

  public enum Sort {
    asc,
    desc
  }

  @Override
  public String toString() {
    return GsonCreator.create().toJson(this);
  }
}
