package com.weibo.dip.analysisql.connector;

import com.weibo.dip.analysisql.dsl.Parser;
import com.weibo.dip.analysisql.dsl.request.GetDimensionValuesRequest;
import com.weibo.dip.analysisql.dsl.request.GetDimensionsRequest;
import com.weibo.dip.analysisql.dsl.request.GetMetricsRequest;
import com.weibo.dip.analysisql.dsl.request.GetTopicsRequest;
import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.metric.MetricCalculator;
import com.weibo.dip.analysisql.response.Response;
import com.weibo.dip.analysisql.response.Row;
import com.weibo.dip.analysisql.response.column.StringColumn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javafx.util.Pair;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** DefaultConnector. */
public class DefaultConnector implements Connector {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConnector.class);

  protected Map<String, Metadata> metadatas = new HashMap<>();

  public void register(Metadata metadata) {
    metadatas.put(metadata.getTopic(), metadata);
  }

  @Override
  public Response getTopics(GetTopicsRequest request) {
    String sessionId = request.getSessionId();

    Response response = new Response();

    response.setSessionId(sessionId);
    response.setCode(200);
    response.setMsg(null);

    StopWatch watch = new StopWatch();
    watch.start();

    for (String topic : metadatas.keySet()) {
      Row row = new Row();
      row.add(new StringColumn(Parser.TOPIC, topic));

      response.add(row);
    }

    watch.stop();
    LOGGER.info(
        "sessionId: {}, query: {}, time: {} ms",
        sessionId,
        request,
        watch.getTime(TimeUnit.MILLISECONDS));

    return response;
  }

  @Override
  public Response getDimensions(GetDimensionsRequest request) {
    String sessionId = request.getSessionId();

    Response response = new Response();

    response.setSessionId(sessionId);
    response.setCode(200);
    response.setMsg(null);

    StopWatch watch = new StopWatch();
    watch.start();

    String topic = request.getTopic();
    if (metadatas.containsKey(topic)) {
      List<Pair<String, String>> dimensions = metadatas.get(topic).getDimensions();
      if (CollectionUtils.isNotEmpty(dimensions)) {
        for (Pair<String, String> dimension : dimensions) {
          Row row = new Row();

          row.add(new StringColumn(Parser.NAME, dimension.getKey()));
          row.add(new StringColumn(Parser.ALIAS, dimension.getValue()));

          response.add(row);
        }
      }
    }

    watch.stop();
    LOGGER.info(
        "sessionId: {}, query: {}, time: {} ms",
        sessionId,
        request,
        watch.getTime(TimeUnit.MILLISECONDS));

    return response;
  }

  @Override
  public Response getDimensionValues(GetDimensionValuesRequest request) {
    String sessionId = request.getSessionId();

    Response response = new Response();

    response.setSessionId(sessionId);
    response.setCode(200);
    response.setMsg(null);

    StopWatch watch = new StopWatch();
    watch.start();

    String topic = request.getTopic();
    String dimension = request.getDimension();

    if (metadatas.containsKey(topic)) {
      List<String> values = metadatas.get(topic).getDimensionValues(dimension);
      if (CollectionUtils.isNotEmpty(values)) {
        for (String value : values) {
          Row row = new Row();
          row.add(new StringColumn(Parser.VALUE, value));

          response.add(row);
        }
      }
    }

    watch.stop();
    LOGGER.info(
        "sessionId: {}, query: {}, time: {} ms",
        sessionId,
        request,
        watch.getTime(TimeUnit.MILLISECONDS));

    return response;
  }

  @Override
  public Response getMetrics(GetMetricsRequest request) {
    String sessionId = request.getSessionId();

    Response response = new Response();

    response.setSessionId(sessionId);
    response.setCode(200);
    response.setMsg(null);

    StopWatch watch = new StopWatch();
    watch.start();

    String topic = request.getTopic();
    if (metadatas.containsKey(topic)) {
      List<Pair<String, String>> metrics = metadatas.get(topic).getMetrics();
      if (CollectionUtils.isNotEmpty(metrics)) {
        for (Pair<String, String> metric : metrics) {
          Row row = new Row();

          row.add(new StringColumn(Parser.NAME, metric.getKey()));
          row.add(new StringColumn(Parser.ALIAS, metric.getValue()));

          response.add(row);
        }
      }
    }

    watch.stop();
    LOGGER.info(
        "sessionId: {}, query: {}, time: {} ms",
        sessionId,
        request,
        watch.getTime(TimeUnit.MILLISECONDS));

    return response;
  }

  @Override
  public Response query(QueryRequest request) {
    String sessionId = request.getSessionId();

    Response response = new Response();

    response.setSessionId(sessionId);
    response.setCode(200);
    response.setMsg(null);

    try {
      StopWatch watch = new StopWatch();
      watch.start();

      String topic = request.getTopic();
      String metric = request.getMetric();

      Metadata metadata = metadatas.get(topic);
      assert Objects.nonNull(metadata);

      MetricCalculator calculator = metadata.getCalculator(metric);
      assert Objects.nonNull(calculator);

      List<Row> rows = calculator.calculate(request);
      if (CollectionUtils.isNotEmpty(rows)) {
        for (Row row : rows) {
          response.add(row);
        }
      }

      watch.stop();
      LOGGER.info(
          "sessionId: {}, query: {}, time: {} ms",
          sessionId,
          request,
          watch.getTime(TimeUnit.MILLISECONDS));
    } catch (Exception e) {
      LOGGER.info("query error: {}", ExceptionUtils.getStackTrace(e));

      response.setCode(500);
      response.setMsg(e.getMessage());
    }

    return response;
  }
}
