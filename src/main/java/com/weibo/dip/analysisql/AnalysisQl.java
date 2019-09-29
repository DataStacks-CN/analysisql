package com.weibo.dip.analysisql;

import com.weibo.dip.analysisql.dsl.Parser;
import com.weibo.dip.analysisql.dsl.request.GetDimentionValuesRequest;
import com.weibo.dip.analysisql.dsl.request.GetDimentionsRequest;
import com.weibo.dip.analysisql.dsl.request.GetMetricsRequest;
import com.weibo.dip.analysisql.dsl.request.GetTopicsRequest;
import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.dsl.request.Request;

/** @author yurun */
public class AnalysisQl {
  private Connector connector;

  public AnalysisQl(Connector connector) {
    this.connector = connector;
  }

  public Response request(String dsl) {
    Parser parser = new Parser(connector);
    Request request = parser.parse(dsl);

    Response response = null;

    switch (request.getType()) {
      case Request.GET_TOPICS:
        response = connector.getTopics((GetTopicsRequest) request);
        break;

      case Request.GET_DIMENTIONS:
        response = connector.getDimentions((GetDimentionsRequest) request);
        break;

      case Request.GET_DIMENTION_VALUES:
        response = connector.getDimentionValues((GetDimentionValuesRequest) request);
        break;

      case Request.GET_METRICS:
        response = connector.getMetrics((GetMetricsRequest) request);
        break;

      case Request.QUERY:
        response = connector.query((QueryRequest) request);
        break;
    }

    return response;
  }
}
