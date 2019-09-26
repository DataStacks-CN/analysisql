package com.weibo.dip.analysisql.dsl.request;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class QueryRequest extends Request {
  private String topic;
  private Interval interval;
  private Granularity granularity;
  private String[] metrics;
  private Filter where;
  private String[] groups;
  private Filter having;
  private Order[] orders;
  private int limit;

  public QueryRequest() {
    super(Request.QUERY);
  }

  public QueryRequest(
      String topic,
      Interval interval,
      Granularity granularity,
      String[] metrics,
      Filter where,
      String[] groups,
      Filter having,
      Order[] orders,
      int limit) {
    this.topic = topic;
    this.interval = interval;
    this.granularity = granularity;
    this.metrics = metrics;
    this.where = where;
    this.groups = groups;
    this.having = having;
    this.orders = orders;
    this.limit = limit;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public Interval getInterval() {
    return interval;
  }

  public void setInterval(Interval interval) {
    this.interval = interval;
  }

  public Granularity getGranularity() {
    return granularity;
  }

  public void setGranularity(Granularity granularity) {
    this.granularity = granularity;
  }

  public String[] getMetrics() {
    return metrics;
  }

  public void setMetrics(String[] metrics) {
    this.metrics = metrics;
  }

  public Filter getWhere() {
    return where;
  }

  public void setWhere(Filter where) {
    this.where = where;
  }

  public String[] getGroups() {
    return groups;
  }

  public void setGroups(String[] groups) {
    this.groups = groups;
  }

  public Filter getHaving() {
    return having;
  }

  public void setHaving(Filter having) {
    this.having = having;
  }

  public Order[] getOrders() {
    return orders;
  }

  public void setOrders(Order[] orders) {
    this.orders = orders;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }
}
