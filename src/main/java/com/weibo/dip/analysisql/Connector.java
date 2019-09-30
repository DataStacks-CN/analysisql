package com.weibo.dip.analysisql;

import com.weibo.dip.analysisql.dsl.request.GetDimentionValuesRequest;
import com.weibo.dip.analysisql.dsl.request.GetDimentionsRequest;
import com.weibo.dip.analysisql.dsl.request.GetMetricsRequest;
import com.weibo.dip.analysisql.dsl.request.GetTopicsRequest;
import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.response.Response;

/** Connector. */
public interface Connector {
  Response getTopics(GetTopicsRequest request);

  Response getDimentions(GetDimentionsRequest request);

  Response getDimentionValues(GetDimentionValuesRequest request);

  Response getMetrics(GetMetricsRequest request);

  Response query(QueryRequest request);
}
