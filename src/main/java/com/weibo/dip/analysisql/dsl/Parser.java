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

/** Parser. */
public class Parser {
  public static final String SESSION_ID = "sessionId";
  public static final String TYPE = "type";
  public static final String TOPIC = "topic";
  public static final String DIMENTION = "dimention";

  public static final String INTERVAL = "interval";
  public static final String START = "start";
  public static final String END = "end";
  public static final FastDateFormat DATE_FORMAT =
      FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

  public static final String GRANULARITY = "granularity";
  public static final String DATA = "data";
  public static final String UNIT = "unit";

  public static final String METRIC = "metric";
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

  public static final String SORT = "sort";
  public static final String ASC = "asc";
  public static final String DESC = "desc";

  private Connector connector;

  public Parser(Connector connector) {
    this.connector = connector;
  }

  /**
   * Parse dsl to Request.
   *
   * @param dsl json
   * @return request
   * @throws SyntaxException if syntax error
   */
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

    if (json.has(SESSION_ID)
        && json.get(SESSION_ID).isJsonPrimitive()
        && json.getAsJsonPrimitive(SESSION_ID).isString()) {
      request.setSessionId(json.getAsJsonPrimitive(SESSION_ID).getAsString());
    }

    return request;
  }

  private Request parseGetTopics(JsonObject json) {
    return new GetTopicsRequest();
  }

  private Request parseGetDimentions(JsonObject json) {
    if (!json.has(TOPIC)
        || !json.get(TOPIC).isJsonPrimitive()
        || !json.getAsJsonPrimitive(TOPIC).isString()) {
      throw new SyntaxException("Type getDimentions, property 'topic'(string) must be set");
    }

    String topic = json.getAsJsonPrimitive(TOPIC).getAsString();

    return new GetDimentionsRequest(topic);
  }

  private Request parseGetDimentionValues(JsonObject json) {
    if (!json.has(TOPIC)
        || !json.get(TOPIC).isJsonPrimitive()
        || !json.getAsJsonPrimitive(TOPIC).isString()
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
        || !json.get(TOPIC).isJsonPrimitive()
        || !json.getAsJsonPrimitive(TOPIC).isString()) {
      throw new SyntaxException("Type getMetrics, property 'topic'(string) must be set");
    }

    String topic = json.getAsJsonPrimitive(TOPIC).getAsString();

    return new GetMetricsRequest(topic);
  }

  private Request parseQuery(JsonObject json) {
    if (!json.has(TOPIC)
        || !json.get(TOPIC).isJsonPrimitive()
        || !json.get(TOPIC).getAsJsonPrimitive().isString()
        || !json.has(INTERVAL)
        || !json.get(INTERVAL).isJsonObject()
        || !(json.getAsJsonObject(INTERVAL).entrySet().size() > 0)
        || !json.has(GRANULARITY)
        || !json.get(GRANULARITY).isJsonObject()
        || !(json.getAsJsonObject(GRANULARITY).entrySet().size() > 0)
        || !json.has(METRIC)
        || !json.get(METRIC).isJsonPrimitive()
        || !json.getAsJsonPrimitive(METRIC).isString()) {
      throw new SyntaxException(
          "Type query, property "
              + "'topic'(string), 'interval'(json), "
              + "'granularity'(object), 'metric'(string) must be set");
    }

    QueryRequest queryRequest = new QueryRequest();

    /*
     topic
    */
    String topic = json.getAsJsonPrimitive(TOPIC).getAsString();
    queryRequest.setTopic(topic);

    /*
     interval
    */
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
          "Type query/interval, property "
              + "'start'(string), 'end'(string) must be in 'yyyy-MM-dd HH:mm:ss' format");
    }

    Interval interval = new Interval(start, end);
    queryRequest.setInterval(interval);

    /*
     granularity
    */
    JsonObject granularityObj = json.getAsJsonObject(GRANULARITY);
    if (!granularityObj.has(DATA)
        || !granularityObj.get(DATA).isJsonPrimitive()
        || !granularityObj.getAsJsonPrimitive(DATA).isNumber()
        || !granularityObj.has(UNIT)
        || !granularityObj.get(UNIT).isJsonPrimitive()
        || !granularityObj.getAsJsonPrimitive(UNIT).isString()) {
      throw new SyntaxException(
          "Type query/granularity, property " + "'data'(int), 'unit'(string) must be set");
    }

    int data = granularityObj.getAsJsonPrimitive(DATA).getAsInt();
    Granularity.Unit unit;

    try {
      unit = Granularity.Unit.valueOf(granularityObj.getAsJsonPrimitive(UNIT).getAsString());
    } catch (IllegalArgumentException e) {
      throw new SyntaxException(
          "Type query, property 'granularity' must be in '1s/2m/3h/4d/5w/6M/1q/1y' format");
    }

    Granularity granularity = new Granularity(data, unit);
    queryRequest.setGranularity(granularity);

    /*
     metric
    */
    queryRequest.setMetric(json.get(METRIC).getAsString());

    /*
     where
    */
    Filter where = null;
    if (json.has(WHERE) && !json.get(WHERE).isJsonNull()) {
      where = new FilterParser(WHERE, connector).parse(json.get(WHERE));
    }

    queryRequest.setWhere(where);

    /*
     groups
    */
    String[] groups = null;
    if (json.has(GROUPS) && !json.get(GROUPS).isJsonNull()) {
      if (!json.get(GROUPS).isJsonArray()) {
        throw new SyntaxException("Type query, property 'groups'(array) must be set");
      }

      JsonArray groupArray = json.getAsJsonArray(GROUPS);

      groups = new String[groupArray.size()];

      for (int index = 0; index < groupArray.size(); index++) {
        JsonElement item = groupArray.get(index);
        if (!item.isJsonPrimitive() || !item.getAsJsonPrimitive().isString()) {
          throw new SyntaxException("Type query, property 'groups' must be a string array");
        }

        groups[index] = item.getAsJsonPrimitive().getAsString();
      }
    }

    queryRequest.setGroups(groups);

    /*
     having
    */
    Filter having = null;
    if (json.has(HAVING) && !json.get(HAVING).isJsonNull()) {
      having = new FilterParser(HAVING, connector).parse(json.get(HAVING));
    }

    queryRequest.setHaving(having);

    /*
     order
    */
    Order[] orders = null;
    if (json.has(ORDERS) && !json.get(ORDERS).isJsonNull()) {
      if (!json.get(ORDERS).isJsonArray()) {
        throw new SyntaxException("Type query, property 'orders'(array) must be set");
      }

      JsonArray orderArray = json.getAsJsonArray(ORDERS);

      orders = new Order[orderArray.size()];

      for (int index = 0; index < orderArray.size(); index++) {
        JsonElement item = orderArray.get(index);
        if (!item.isJsonObject()
            || !item.getAsJsonObject().has(NAME)
            || !item.getAsJsonObject().get(NAME).isJsonPrimitive()
            || !item.getAsJsonObject().getAsJsonPrimitive(NAME).isString()
            || !item.getAsJsonObject().has(SORT)
            || !item.getAsJsonObject().get(SORT).isJsonPrimitive()
            || !item.getAsJsonObject().getAsJsonPrimitive(SORT).isString()) {
          throw new SyntaxException(
              "Type query, property 'orders'(object)'s order "
                  + "must has property(string) 'name' and 'sort'");
        }

        JsonObject order = item.getAsJsonObject();

        String name = order.getAsJsonPrimitive(NAME).getAsString();
        String sort = order.getAsJsonPrimitive(SORT).getAsString();
        if (!(sort.equals(ASC) || sort.equals(DESC))) {
          throw new SyntaxException(
              "Type query, property 'orders'(object)'s order" + " 'sort' must be 'asc/desc'");
        }

        orders[index] = new Order(name, Order.Sort.valueOf(sort));
      }
    }

    queryRequest.setOrders(orders);

    /*
     limit
    */
    int limit = -1;
    if (json.has(LIMIT) && !json.get(LIMIT).isJsonNull()) {
      if (!json.get(LIMIT).isJsonPrimitive()
          || !json.getAsJsonPrimitive(LIMIT).isNumber()
          || ((json.getAsJsonPrimitive(LIMIT).getAsNumber() instanceof Float)
              || (json.getAsJsonPrimitive(LIMIT).getAsNumber() instanceof Double))) {
        throw new SyntaxException("Type query, property 'limit'(int) must be set");
      }

      limit = json.get(LIMIT).getAsInt();
    }

    queryRequest.setLimit(limit);

    return queryRequest;
  }
}
