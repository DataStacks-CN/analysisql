package com.weibo.dip.analysisql.connector;

import com.weibo.dip.analysisql.dsl.request.GetDimensionValuesRequest;
import com.weibo.dip.analysisql.dsl.request.GetDimensionsRequest;
import com.weibo.dip.analysisql.dsl.request.GetMetricsRequest;
import com.weibo.dip.analysisql.dsl.request.GetTopicsRequest;
import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.response.Response;

import java.io.Closeable;

/**
 * Connector.
 */
public interface Connector extends Closeable {
  Response getTopics(GetTopicsRequest request);

  Response getDimensions(GetDimensionsRequest request);

  Response getDimensionValues(GetDimensionValuesRequest request);

  Response getMetrics(GetMetricsRequest request);

  Response query(QueryRequest request);
}
