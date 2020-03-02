package com.weibo.dip.analysisql.dsl.request;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** GetDimensionValuesRequest. */
public class GetDimensionValuesRequest extends Request {
  private String topic;
  private String dimension;
  private Filter where;

  public GetDimensionValuesRequest() {
    super(Request.GET_DIMENSION_VALUES);
  }

  /**
   * Initialize a instance with topic and dimension.
   *
   * @param topic topic
   * @param dimension dimension
   */
  public GetDimensionValuesRequest(String topic, String dimension) {
    this(topic, dimension, null);
  }

  /**
   * Initialize a instance with topic, dimension and filter.
   *
   * @param topic topic
   * @param dimension dimension
   * @param where filter
   */
  public GetDimensionValuesRequest(String topic, String dimension, Filter where) {
    this();

    this.topic = topic;
    this.dimension = dimension;
    this.where = where;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getDimension() {
    return dimension;
  }

  public void setDimension(String dimension) {
    this.dimension = dimension;
  }

  public Filter getWhere() {
    return where;
  }

  public void setWhere(Filter where) {
    this.where = where;
  }
}
