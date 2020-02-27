package com.weibo.dip.analysisql.dsl.request;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** GetDimentionValuesRequest. */
public class GetDimentionValuesRequest extends Request {
  private String topic;
  private String dimention;
  private Filter where;

  public GetDimentionValuesRequest() {
    super(Request.GET_DIMENTION_VALUES);
  }

  /**
   * Initializes a instance with topic and dimention.
   *
   * @param topic topic
   * @param dimention dimention
   */
  public GetDimentionValuesRequest(String topic, String dimention) {
    this(topic, dimention, null);
  }

  /**
   * Initializes a instance with topic, dimention and filter.
   *
   * @param topic topic
   * @param dimention dimention
   * @param where filter
   */
  public GetDimentionValuesRequest(String topic, String dimention, Filter where) {
    this();

    this.topic = topic;
    this.dimention = dimention;
    this.where = where;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getDimention() {
    return dimention;
  }

  public void setDimention(String dimention) {
    this.dimention = dimention;
  }

  public Filter getWhere() {
    return where;
  }

  public void setWhere(Filter where) {
    this.where = where;
  }
}
