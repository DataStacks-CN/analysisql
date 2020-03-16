package com.weibo.dip.analysisql.connector;

/** Metric. */
public class Metric {
  private String name;
  private String alias;
  private String desc;

  public Metric() {}

  /**
   * Initialize a instance with name, alias, desc.
   *
   * @param name name
   * @param alias alias
   * @param desc desc
   */
  public Metric(String name, String alias, String desc) {
    this.name = name;
    this.alias = alias;
    this.desc = desc;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
