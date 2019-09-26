package com.weibo.dip.analysisql.dsl.request;

/** @author yurun */
public class GetDimentionValuesRequest extends Request {
  private String topic;
  private String dimention;

  public GetDimentionValuesRequest() {
    super(Request.GET_DIMENTION_VALUES);
  }

  public GetDimentionValuesRequest(String topic, String dimention) {
    this();

    this.topic = topic;
    this.dimention = dimention;
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
}
