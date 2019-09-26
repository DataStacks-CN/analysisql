package com.weibo.dip.analysisql.dsl.request;

/** @author yurun */
public class GetDimentionsRequest extends Request {
  private String topic;

  public GetDimentionsRequest() {
    super(Request.GET_DIMENTIONS);
  }

  public GetDimentionsRequest(String topic) {
    this();

    this.topic = topic;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }
}
