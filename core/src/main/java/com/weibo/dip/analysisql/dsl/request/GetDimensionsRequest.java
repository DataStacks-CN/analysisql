package com.weibo.dip.analysisql.dsl.request;

/** GetDimensionsRequest. */
public class GetDimensionsRequest extends Request {
  private String topic;

  public GetDimensionsRequest() {
    super(Request.GET_DIMENSIONS);
  }

  /**
   * Initialize a instance with topic.
   *
   * @param topic topic
   */
  public GetDimensionsRequest(String topic) {
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
