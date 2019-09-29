package com.weibo.dip.analysisql.dsl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.weibo.dip.analysisql.Connector;
import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.FilterParser;
import com.weibo.dip.analysisql.dsl.request.GetDimentionValuesRequest;
import com.weibo.dip.analysisql.dsl.request.GetDimentionsRequest;
import com.weibo.dip.analysisql.dsl.request.GetMetricsRequest;
import com.weibo.dip.analysisql.dsl.request.GetTopicsRequest;
import com.weibo.dip.analysisql.dsl.request.Granularity;
import com.weibo.dip.analysisql.dsl.request.Interval;
import com.weibo.dip.analysisql.dsl.request.Order;
import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.dsl.request.Request;
import com.weibo.dip.analysisql.exception.SyntaxException;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.time.FastDateFormat;

/** @author yurun */
public class Parser {
  public static final String TYPE = "type";
  public static final String TOPIC = "topic";
  public static final String DIMENTION = "dimention";

  public static final String INTERVAL = "interval";
  public static final String START = "start";
  public static final String END = "end";
  public static final FastDateFormat DATE_FORMAT =
      FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

  public static final String GRANULARITY = "granularity";
  public static final String METRICS = "metrics";
  public static final String WHERE = "where";
  public static final String GROUPS = "groups";
  public static final String HAVING = "having";
  public static final String ORDERS = "orders";
  public static final String LIMIT = "limit";

  public static final String OPERATOR = "operator";

  public static final String FILTERS = "filters";
  public static final String FILTER = "filter";

  public static final String NAME = "name";
  public static final String VALUE = "value";
  public static final String VALUES = "values";
  public static final String PATTERN = "pattern";

  private Connector connector;

  public Parser(Connector connector) {
    this.connector = connector;
  }

  public Request parse(String dsl) throws SyntaxException {
    JsonParser parser = new JsonParser();

    JsonElement element;

    try {
      element = parser.parse(dsl);
    } catch (JsonSyntaxException e) {
      throw new SyntaxException("DSL must be in json format", e);
    }

    if (!element.isJsonObject()) {
      throw new SyntaxException("DSL must be a json object");
    }
    element = parser.parse(dsl);

    JsonObject json = element.getAsJsonObject();

    if (!json.has(TYPE)
        || !json.get(TYPE).isJsonPrimitive()
        || !json.getAsJsonPrimitive(TYPE).isString()) {
      throw new SyntaxException("Property 'type'(string) must be set");
    }

    String type = json.getAsJsonPrimitive(TYPE).getAsString();

    Request request;

    switch (type) {
      case Request.GET_TOPICS:
        request = parseGetTopics(json);
        break;
      case Request.GET_DIMENTIONS:
        request = parseGetDimentions(json);
        break;
      case Request.GET_DIMENTION_VALUES:
        request = parseGetDimentionValues(json);
        break;
      case Request.GET_METRICS:
        request = parseGetMetrics(json);
        break;
      case Request.QUERY:
        request = parseQuery(json);
        break;
      default:
        throw new SyntaxException("Unsupported type '" + type + "'");
    }

    return request;
  }

  private Request parseGetTopics(JsonObject json) {
    return new GetTopicsRequest();
  }

  private Request parseGetDimentions(JsonObject json) {
    if (!json.has(TOPIC)
        || !json.get(TYPE).isJsonPrimitive()
        || !json.getAsJsonPrimitive(TYPE).isString()) {
      throw new SyntaxException("Type getDimentions, property 'topic'(string) must be set");
    }

    String topic = json.getAsJsonPrimitive(TOPIC).getAsString();

    return new GetDimentionsRequest(topic);
  }

  private Request parseGetDimentionValues(JsonObject json) {
    if (!json.has(TOPIC)
        || !json.get(TYPE).isJsonPrimitive()
        || !json.getAsJsonPrimitive(TYPE).isString()
        || !json.has(DIMENTION)
        || !json.get(DIMENTION).isJsonPrimitive()
        || !json.getAsJsonPrimitive(DIMENTION).isString()) {
      throw new SyntaxException(
          "Type getDimentionValues, property 'topic'(string), 'dimention'(string) must be set");
    }

    String topic = json.getAsJsonPrimitive(TOPIC).getAsString();
    String dimention = json.getAsJsonPrimitive(DIMENTION).getAsString();

    return new GetDimentionValuesRequest(topic, dimention);
  }

  private Request parseGetMetrics(JsonObject json) {
    if (!json.has(TOPIC)
        || !json.get(TYPE).isJsonPrimitive()
        || !json.getAsJsonPrimitive(TYPE).isString()) {
      throw new SyntaxException("Type getMetrics, property 'topic'(string) must be set");
    }

    String topic = json.getAsJsonPrimitive(TOPIC).getAsString();

    return new GetMetricsRequest(topic);
  }

  private Request parseQuery(JsonObject json) {
    if (!json.has(TOPIC)
        || !json.get(TYPE).isJsonPrimitive()
        || !json.get(TYPE).getAsJsonPrimitive().isString()
        || !json.has(INTERVAL)
        || !json.get(INTERVAL).isJsonObject()
        || !json.has(GRANULARITY)
        || !json.get(GRANULARITY).isJsonPrimitive()
        || !json.getAsJsonPrimitive(GRANULARITY).isString()
        || !json.has(METRICS)
        || !json.get(METRICS).isJsonArray()) {
      throw new SyntaxException(
          "Type query, property 'topic'(string), 'interval'(json), 'granularity'(string), 'metrics'(array) must be set");
    }

    String topic = json.getAsJsonPrimitive(TOPIC).getAsString();

    JsonObject intervalOjb = json.getAsJsonObject(INTERVAL);
    if (!intervalOjb.has(START)
        || !intervalOjb.get(START).isJsonPrimitive()
        || !intervalOjb.getAsJsonPrimitive(START).isString()
        || !intervalOjb.has(END)
        || !intervalOjb.get(END).isJsonPrimitive()
        || !intervalOjb.getAsJsonPrimitive(END).isString()) {
      throw new SyntaxException(
          "Type query/interval, property 'start'(string), 'end'(string) must be set");
    }

    Date start;
    Date end;

    try {
      start = DATE_FORMAT.parse(intervalOjb.getAsJsonPrimitive(START).getAsString());
      end = DATE_FORMAT.parse(intervalOjb.getAsJsonPrimitive(END).getAsString());
    } catch (ParseException e) {
      throw new SyntaxException(
          "Type query/interval, property 'start'(string), 'end'(string) must be in 'yyyy-MM-dd HH:mm:ss' format");
    }

    Interval interval = new Interval(start, end);

    String granularityStr = json.getAsJsonPrimitive(GRANULARITY).getAsString();

    String dataStr = granularityStr.substring(0, granularityStr.length() - 1);
    String unitStr = granularityStr.substring(granularityStr.length() - 1);

    int data;
    Granularity.Unit unit;

    try {
      data = Integer.valueOf(dataStr);
      unit = Granularity.Unit.valueOf(unitStr);
    } catch (IllegalArgumentException e) {
      throw new SyntaxException(
          "Type query, property 'granularity' must be in '1s/2m/3h/4d/5w/6M/1q/1y' format");
    }

    Granularity granularity = new Granularity(data, unit);

    JsonArray metricsArray = json.getAsJsonArray(METRICS);

    String[] metrics = new String[metricsArray.size()];

    for (int index = 0; index < metricsArray.size(); index++) {
      JsonElement item = metricsArray.get(index);
      if (!item.isJsonPrimitive() || !item.getAsJsonPrimitive().isString()) {
        throw new SyntaxException("Type query, property 'metrics' must be a string array");
      }

      metrics[index] = item.getAsJsonPrimitive().getAsString();
    }

    Filter where = null;
    if (json.has(WHERE)) {
      try {
        where = new FilterParser(WHERE, connector).parse(json.get(WHERE));
      } catch (SyntaxException e) {
        throw e;
      }
    }

    String[] groups = null;
    if (json.has(GROUPS)) {}

    Filter having = null;
    if (json.has(HAVING)) {
      try {
        having = new FilterParser(HAVING, connector).parse(json.get(HAVING));
      } catch (Exception e) {
        throw e;
      }
    }

    Order[] orders = null;
    if (json.has(ORDERS)) {}

    int limit = 0;
    if (json.has(LIMIT)) {}

    QueryRequest queryRequest = new QueryRequest();

    queryRequest.setTopic(topic);
    queryRequest.setInterval(interval);
    queryRequest.setGranularity(granularity);
    queryRequest.setMetrics(metrics);
    queryRequest.setWhere(where);
    queryRequest.setGroups(groups);
    queryRequest.setHaving(having);
    queryRequest.setOrders(orders);
    queryRequest.setLimit(limit);

    return null;
  }
}
