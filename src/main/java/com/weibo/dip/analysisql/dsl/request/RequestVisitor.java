package com.weibo.dip.analysisql.dsl.request;

import java.util.Objects;

/** RequestVisitor. */
public abstract class RequestVisitor<T> {
  protected abstract T visitGetTopicsRequest(GetTopicsRequest request);

  protected abstract T visitGetDimentionsRequest(GetDimentionsRequest request);

  protected abstract T visitGetDimentionValuesRequest(GetDimentionValuesRequest request);

  protected abstract T visitGetMetricsRequest(GetMetricsRequest request);

  protected abstract T visitQueryRequest(QueryRequest request);

  /**
   * Visit request.
   *
   * @param request request
   * @return result
   */
  public T visit(Request request) {
    if (Objects.isNull(request)) {
      return null;
    }

    String type = request.getType();

    switch (type) {
      case Request.GET_TOPICS:
        return visitGetTopicsRequest((GetTopicsRequest) request);
      case Request.GET_DIMENTIONS:
        return visitGetDimentionsRequest((GetDimentionsRequest) request);
      case Request.GET_DIMENTION_VALUES:
        return visitGetDimentionValuesRequest((GetDimentionValuesRequest) request);
      case Request.GET_METRICS:
        return visitGetMetricsRequest((GetMetricsRequest) request);
      case Request.QUERY:
        return visitQueryRequest((QueryRequest) request);
      default:
        return null;
    }
  }
}
