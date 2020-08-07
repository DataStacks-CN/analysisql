package com.weibo.dip.analysis.view;

import com.weibo.dip.analysisql.connector.Connector;
import com.weibo.dip.analysisql.connector.Dimension;
import com.weibo.dip.analysisql.connector.Metadata;
import com.weibo.dip.analysisql.connector.Metric;
import com.weibo.dip.analysisql.dsl.Parser;
import com.weibo.dip.analysisql.dsl.request.GetDimensionValuesRequest;
import com.weibo.dip.analysisql.dsl.request.GetDimensionsRequest;
import com.weibo.dip.analysisql.dsl.request.GetMetricsRequest;
import com.weibo.dip.analysisql.dsl.request.GetTopicsRequest;
import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.metric.MetricCalculator;
import com.weibo.dip.analysisql.metric.SqlBasedCalculator;
import com.weibo.dip.analysisql.response.Response;
import com.weibo.dip.analysisql.response.Row;
import com.weibo.dip.analysisql.response.column.StringColumn;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** DefaultConnector. */
public class DefaultConnector implements Connector {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConnector.class);

  protected ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  protected Lock readLock = readWriteLock.readLock();
  protected Lock writeLock = readWriteLock.writeLock();

  protected Map<String, Metadata> metadatas = new HashMap<>();

  /**
   * Register a metadata.
   *
   * @param metadata Metadata
   */
  public void register(Metadata metadata) {
    writeLock.lock();

    try {
      metadatas.put(metadata.getTopic(), metadata);
    } finally {
      writeLock.unlock();
    }
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

    readLock.lock();

    try {
      for (Metadata metadata : metadatas.values()) {
        Row row = new Row();

        row.add(new StringColumn(Parser.TOPIC, metadata.getTopic()));
        row.add(new StringColumn(Parser.ALIAS, metadata.getAlias()));
        row.add(new StringColumn(Parser.DESC, metadata.getDesc()));

        response.add(row);
      }
    } finally {
      readLock.unlock();
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

    List<Dimension> dimensions = null;

    readLock.lock();

    try {
      String topic = request.getTopic();
      if (metadatas.containsKey(topic)) {
        dimensions = metadatas.get(topic).getDimensions();
      } else {
        LOGGER.warn("sessionId: {}, unknown topic: {}", sessionId, topic);
      }
    } finally {
      readLock.unlock();
    }

    if (CollectionUtils.isNotEmpty(dimensions)) {
      for (Dimension dimension : dimensions) {
        Row row = new Row();

        row.add(new StringColumn(Parser.NAME, dimension.getName()));
        row.add(new StringColumn(Parser.ALIAS, dimension.getAlias()));
        row.add(new StringColumn(Parser.DESC, dimension.getDesc()));

        response.add(row);
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

    List<String> values = null;

    readLock.lock();

    try {
      String topic = request.getTopic();
      String dimension = request.getDimension();

      if (metadatas.containsKey(topic)) {
        values = metadatas.get(topic).getDimensionValues(dimension);
      } else {
        LOGGER.warn("sessionId: {}, unknown topic: {}", sessionId, topic);
      }
    } finally {
      readLock.unlock();
    }

    if (CollectionUtils.isNotEmpty(values)) {
      for (String value : values) {
        Row row = new Row();
        row.add(new StringColumn(Parser.VALUE, value));

        response.add(row);
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

    Metadata metadata = null;
    List<Metric> metrics = null;

    readLock.lock();

    try {
      String topic = request.getTopic();
      if (metadatas.containsKey(topic)) {
        metadata = metadatas.get(topic);
        metrics = metadata.getMetrics();
      } else {
        LOGGER.warn("sessionId: {}, unknown topic: {}", sessionId, topic);
      }
    } finally {
      readLock.unlock();
    }

    if (CollectionUtils.isNotEmpty(metrics)) {
      for (Metric metric : metrics) {
        String name = metric.getName();

        Row row = new Row();

        row.add(new StringColumn(Parser.NAME, name));
        row.add(new StringColumn(Parser.ALIAS, metric.getAlias()));
        row.add(new StringColumn(Parser.DESC, metric.getDesc()));

        String rule = Parser.UNKNOWN;

        if (metadata instanceof View) {
          List<Table> tables = ((View) metadata).getTableUsingMetric(name);
          if (CollectionUtils.isNotEmpty(tables)) {
            MetricCalculator calculator = tables.get(0).getCalculator(name);
            if (calculator instanceof SqlBasedCalculator) {
              String sql = ((SqlBasedCalculator) calculator).getSql();
              if (Objects.nonNull(sql)) {
                rule = sql;
              }
            } else {
              rule = Parser.CUSTOM;
            }
          }
        }

        row.add(new StringColumn(Parser.RULE, rule));

        response.add(row);
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

      Metadata metadata;

      readLock.lock();

      try {
        metadata = metadatas.get(topic);
      } finally {
        readLock.unlock();
      }

      if (Objects.nonNull(metadata)) {
        MetricCalculator calculator = metadata.getCalculator(metric);
        if (Objects.nonNull(calculator)) {
          List<Row> rows = calculator.calculate(request);
          if (CollectionUtils.isNotEmpty(rows)) {
            for (Row row : rows) {
              response.add(row);
            }
          }
        } else {
          LOGGER.warn("sessionId: {}, can't get the calculator in topic: {}", sessionId, topic);
        }
      } else {
        LOGGER.warn("sessionId: {}, unknown topic: {}", sessionId, topic);
      }

      watch.stop();
      LOGGER.info(
          "sessionId: {}, query: {}, time: {} ms",
          sessionId,
          request,
          watch.getTime(TimeUnit.MILLISECONDS));
    } catch (Exception e) {
      LOGGER.info("sessionId: {}, query error: {}", sessionId, ExceptionUtils.getStackTrace(e));

      response.setCode(500);
      response.setMsg(e.getMessage());
    }

    return response;
  }

  @Override
  public void close() throws IOException {
    writeLock.lock();

    try {
      for (Metadata metadata : metadatas.values()) {
        try {
          metadata.close();
        } catch (IOException e) {
          LOGGER.error(
              "Metadata {} close error: {}", metadata.getTopic(), ExceptionUtils.getStackTrace(e));
        }
      }

      metadatas.clear();
    } finally {
      writeLock.unlock();
    }
  }
}
