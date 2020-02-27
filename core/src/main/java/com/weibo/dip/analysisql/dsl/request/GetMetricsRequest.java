package com.weibo.dip.analysisql.dsl.request;

/** GetMetricsRequest. */
public class GetMetricsRequest extends Request {
  private String topic;

  public GetMetricsRequest() {
    super(Request.GET_METRICS);
  }

  /**
   * Initializes a instance with topic.
   *
   * @param topic topic
   */
  public GetMetricsRequest(String topic) {
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
