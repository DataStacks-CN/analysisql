package com.weibo.dip.analysisql;

import com.weibo.dip.analysisql.connector.Connector;
import com.weibo.dip.analysisql.dsl.Parser;
import com.weibo.dip.analysisql.dsl.request.GetDimensionValuesRequest;
import com.weibo.dip.analysisql.dsl.request.GetDimensionsRequest;
import com.weibo.dip.analysisql.dsl.request.GetMetricsRequest;
import com.weibo.dip.analysisql.dsl.request.GetTopicsRequest;
import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.dsl.request.Request;
import com.weibo.dip.analysisql.response.Response;
import java.util.Objects;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

/** AnalysisQl. */
public class AnalysisQl {
  private Connector connector;

  public AnalysisQl(Connector connector) {
    this.connector = connector;
  }

  /**
   * Request with a dsl.
   *
   * @param dsl json
   * @return response
   */
  public Response request(String dsl) {
    if (StringUtils.isEmpty(dsl)) {
      return null;
    }

    Parser parser = new Parser(connector);

    Request request = parser.parse(dsl);

    return request(request);
  }

  /**
   * Request with a instance.
   *
   * @param request request
   * @return response
   */
  public Response request(Request request) {
    if (Objects.isNull(request)) {
      return null;
    }

    if (Objects.isNull(request.getSessionId())) {
      request.setSessionId(UUID.randomUUID().toString());
    }

    switch (request.getType()) {
      case Request.GET_TOPICS:
        return connector.getTopics((GetTopicsRequest) request);

      case Request.GET_DIMENSIONS:
        return connector.getDimensions((GetDimensionsRequest) request);

      case Request.GET_DIMENSION_VALUES:
        return connector.getDimensionValues((GetDimensionValuesRequest) request);

      case Request.GET_METRICS:
        return connector.getMetrics((GetMetricsRequest) request);

      case Request.QUERY:
        return connector.query((QueryRequest) request);
      default:
        throw new UnsupportedOperationException();
    }
  }
}
