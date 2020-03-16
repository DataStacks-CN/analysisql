package com.weibo.dip.analysisql.connector;

/** Dimension. */
public class Dimension {
  private String name;
  private String alias;
  private String desc;

  public Dimension() {}

  /**
   * Initialize a instance with name, alias, desc.
   *
   * @param name name
   * @param alias alias
   * @param desc desc
   */
  public Dimension(String name, String alias, String desc) {
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
