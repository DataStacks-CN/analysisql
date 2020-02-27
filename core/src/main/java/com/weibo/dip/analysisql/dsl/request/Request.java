package com.weibo.dip.analysisql.dsl.request;

import com.weibo.dip.analysisql.util.GsonCreator;
import java.io.Serializable;

/** Request. */
public abstract class Request implements Serializable {
  public static final String GET_TOPICS = "getTopics";
  public static final String GET_DIMENTIONS = "getDimentions";
  public static final String GET_DIMENTION_VALUES = "getDimentionValues";
  public static final String GET_METRICS = "getMetrics";
  public static final String QUERY = "query";

  protected String sessionId;
  protected String type;

  public Request() {}

  public Request(String type) {
    this.type = type;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return GsonCreator.create().toJson(this);
  }
}
