package com.weibo.dip.analysisql.dsl.filter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.weibo.dip.analysisql.connector.Connector;
import com.weibo.dip.analysisql.dsl.Parser;
import com.weibo.dip.analysisql.dsl.filter.in.DoubleInFilter;
import com.weibo.dip.analysisql.dsl.filter.in.LongInFilter;
import com.weibo.dip.analysisql.dsl.filter.in.StringInFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.AndFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.NotFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.OrFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.eq.DoubleEqFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.eq.LongEqFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.eq.StringEqFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ge.DoubleGeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ge.LongGeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ge.StringGeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.gt.DoubleGtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.gt.LongGtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.gt.StringGtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.le.DoubleLeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.le.LongLeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.le.StringLeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.lt.DoubleLtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.lt.LongLtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.lt.StringLtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ne.DoubleNeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ne.LongNeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ne.StringNeFilter;
import com.weibo.dip.analysisql.exception.SyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/** FilterParser. */
public class FilterParser {
  private String context;

  private Connector connector;

  /**
   * Initializes a instance with context and connector.
   *
   * @param context 'where' or 'having'
   * @param connector Connector instance
   */
  public FilterParser(String context, Connector connector) {
    this.context = context;
    this.connector = connector;
  }

  private Filter parseAnd(JsonObject json) {
    return parseAndOrOr(Filter.AND, json);
  }

  private Filter parseOr(JsonObject json) {
    return parseAndOrOr(Filter.OR, json);
  }

  private Filter parseAndOrOr(String operator, JsonObject json) {
    if (!json.has(Parser.FILTERS)
        || !json.get(Parser.FILTERS).isJsonArray()
        || !(json.get(Parser.FILTERS).getAsJsonArray().size() > 0)) {
      throw new SyntaxException(
          "Type query/" + context + "/" + operator + ", property 'filters'(array) must be set");
    }

    List<Filter> filters = new ArrayList<>();

    JsonArray filterArray = json.getAsJsonArray(Parser.FILTERS);

    for (int index = 0; index < filterArray.size(); index++) {
      filters.add(parse(filterArray.get(index)));
    }

    switch (operator) {
      case Filter.AND:
        return new AndFilter(filters);
      case Filter.OR:
        return new OrFilter(filters);
      default:
        return null;
    }
  }

  private Filter parseNot(JsonObject json) {
    if (!json.has(Parser.FILTER) || !json.get(Parser.FILTER).isJsonObject()) {
      throw new SyntaxException(
          "Type query/" + context + "/not, property 'filter'(object) must be set");
    }

    return new NotFilter(parse(json.getAsJsonObject(Parser.FILTER)));
  }

  private Filter parseEq(JsonObject json) {
    return parseEqOrNeOrGtOrLtOrGeOrLe(Filter.EQ, json);
  }

  private Filter parseNe(JsonObject json) {
    return parseEqOrNeOrGtOrLtOrGeOrLe(Filter.NE, json);
  }

  private Filter parseGt(JsonObject json) {
    return parseEqOrNeOrGtOrLtOrGeOrLe(Filter.GT, json);
  }

  private Filter parseLt(JsonObject json) {
    return parseEqOrNeOrGtOrLtOrGeOrLe(Filter.LT, json);
  }

  private Filter parseGe(JsonObject json) {
    return parseEqOrNeOrGtOrLtOrGeOrLe(Filter.GE, json);
  }

  private Filter parseLe(JsonObject json) {
    return parseEqOrNeOrGtOrLtOrGeOrLe(Filter.LE, json);
  }

  private String getType(JsonPrimitive primitive) {
    if (primitive.isString()) {
      return RelationalFilter.STRING;
    }

    Number value = primitive.getAsNumber();

    return ((value instanceof Byte)
            || (value instanceof Short)
            || (value instanceof Integer)
            || (value instanceof Long))
        ? RelationalFilter.LONG
        : RelationalFilter.DOUBLE;
  }

  private Filter parseEqOrNeOrGtOrLtOrGeOrLe(String operator, JsonObject json) {
    if (!json.has(Parser.NAME)
        || !json.get(Parser.NAME).isJsonPrimitive()
        || !json.getAsJsonPrimitive(Parser.NAME).isString()
        || !json.has(Parser.VALUE)
        || !json.get(Parser.VALUE).isJsonPrimitive()
        || !(json.getAsJsonPrimitive(Parser.VALUE).isString()
            || json.getAsJsonPrimitive(Parser.VALUE).isNumber())) {
      throw new SyntaxException(
          "Type query/"
              + context
              + "/"
              + operator
              + ", property 'name'(string), 'value'(string/long/double) must be set");
    }

    String name = json.getAsJsonPrimitive(Parser.NAME).getAsString();
    JsonPrimitive value = json.getAsJsonPrimitive(Parser.VALUE);

    String type = getType(value);

    switch (operator) {
      case Filter.EQ:
        return StringUtils.equals(type, RelationalFilter.STRING)
            ? new StringEqFilter(name, value.getAsString())
            : (StringUtils.equals(type, RelationalFilter.LONG)
                ? new LongEqFilter(name, value.getAsLong())
                : new DoubleEqFilter(name, value.getAsDouble()));
      case Filter.NE:
        return StringUtils.equals(type, RelationalFilter.STRING)
            ? new StringNeFilter(name, value.getAsString())
            : (StringUtils.equals(type, RelationalFilter.LONG)
                ? new LongNeFilter(name, value.getAsLong())
                : new DoubleNeFilter(name, value.getAsDouble()));
      case Filter.GT:
        return StringUtils.equals(type, RelationalFilter.STRING)
            ? new StringGtFilter(name, value.getAsString())
            : (StringUtils.equals(type, RelationalFilter.LONG)
                ? new LongGtFilter(name, value.getAsLong())
                : new DoubleGtFilter(name, value.getAsDouble()));
      case Filter.LT:
        return StringUtils.equals(type, RelationalFilter.STRING)
            ? new StringLtFilter(name, value.getAsString())
            : (StringUtils.equals(type, RelationalFilter.LONG)
                ? new LongLtFilter(name, value.getAsLong())
                : new DoubleLtFilter(name, value.getAsDouble()));
      case Filter.GE:
        return StringUtils.equals(type, RelationalFilter.STRING)
            ? new StringGeFilter(name, value.getAsString())
            : (StringUtils.equals(type, RelationalFilter.LONG)
                ? new LongGeFilter(name, value.getAsLong())
                : new DoubleGeFilter(name, value.getAsDouble()));
      case Filter.LE:
        return StringUtils.equals(type, RelationalFilter.STRING)
            ? new StringLeFilter(name, value.getAsString())
            : (StringUtils.equals(type, RelationalFilter.LONG)
                ? new LongLeFilter(name, value.getAsLong())
                : new DoubleLeFilter(name, value.getAsDouble()));
      default:
        return null;
    }
  }

  private Filter parseStringIn(String name, JsonArray array) {
    String[] values = new String[array.size()];

    for (int index = 0; index < array.size(); index++) {
      JsonElement value = array.get(index);
      if (!value.getAsJsonPrimitive().isString()) {
        throw new SyntaxException(
            "Type query/" + context + "/in, property 'values'(array)'s type must be string");
      }

      values[index] = value.getAsJsonPrimitive().getAsString();
    }

    return new StringInFilter(name, values);
  }

  private Filter parseLongIn(String name, JsonArray array) {
    Long[] values = new Long[array.size()];

    for (int index = 0; index < array.size(); index++) {
      Number value = array.get(index).getAsNumber();
      boolean isLong =
          (value instanceof Byte)
              || (value instanceof Short)
              || (value instanceof Integer)
              || (value instanceof Long);
      if (!isLong) {
        throw new SyntaxException(
            "Type query/" + context + "/in, property 'values'(array)'s type must be long");
      }

      values[index] = value.longValue();
    }

    return new LongInFilter(name, values);
  }

  private Filter parseDoubleIn(String name, JsonArray array) {
    Double[] values = new Double[array.size()];

    for (int index = 0; index < array.size(); index++) {
      Number value = array.get(index).getAsNumber();
      boolean isDouble = (value instanceof Float) || (value instanceof Double);
      if (!isDouble) {
        throw new SyntaxException(
            "Type query/" + context + "/in, property 'values'(array)'s type must be double");
      }

      values[index] = value.doubleValue();
    }

    return new DoubleInFilter(name, values);
  }

  private Filter parseIn(JsonObject json) {
    if (!json.has(Parser.NAME)
        || !json.get(Parser.NAME).isJsonPrimitive()
        || !json.getAsJsonPrimitive(Parser.NAME).isString()
        || !json.has(Parser.VALUES)
        || !json.get(Parser.VALUES).isJsonArray()
        || !(json.get(Parser.VALUES).getAsJsonArray().size() > 0)) {
      throw new SyntaxException(
          "Type query/" + context + "/in, property 'name'(string), 'values'(array) must be set");
    }

    String name = json.getAsJsonPrimitive(Parser.NAME).getAsString();
    JsonArray valueArray = json.getAsJsonArray(Parser.VALUES);

    for (int index = 0; index < valueArray.size(); index++) {
      JsonElement item = valueArray.get(index);
      if (!item.isJsonPrimitive()
          || !(item.getAsJsonPrimitive().isString() || item.getAsJsonPrimitive().isNumber())) {
        throw new SyntaxException(
            "Type query/" + context + "/in, property 'values'(array)'s type must be string/number");
      }
    }

    String type = getType(valueArray.get(0).getAsJsonPrimitive());

    switch (type) {
      case RelationalFilter.STRING:
        return parseStringIn(name, valueArray);
      case RelationalFilter.LONG:
        return parseLongIn(name, valueArray);
      case RelationalFilter.DOUBLE:
        return parseDoubleIn(name, valueArray);
      default:
        return null;
    }
  }

  private Filter parseRegex(JsonObject json) {
    if (!json.has(Parser.NAME)
        || !json.get(Parser.NAME).isJsonPrimitive()
        || !json.getAsJsonPrimitive(Parser.NAME).isString()
        || !json.has(Parser.PATTERN)
        || !json.get(Parser.PATTERN).isJsonPrimitive()
        || !json.getAsJsonPrimitive(Parser.PATTERN).isString()) {
      throw new SyntaxException(
          "Type query/"
              + context
              + "/regex, property 'name'(string), 'pattern'(string) must be set");
    }

    String name = json.getAsJsonPrimitive(Parser.NAME).getAsString();
    String pattern = json.getAsJsonPrimitive(Parser.PATTERN).getAsString();

    return new RegexFilter(name, pattern);
  }

  /**
   * Parse 'where' or 'having' filter json.
   *
   * @param element json
   * @return filter
   * @throws SyntaxException if syntax error
   */
  public Filter parse(JsonElement element) throws SyntaxException {
    if (!element.isJsonObject()) {
      throw new SyntaxException("Type query, property " + context + " must be in json object");
    }

    JsonObject json = element.getAsJsonObject();
    if (!json.has(Parser.OPERATOR)
        || !json.get(Parser.OPERATOR).isJsonPrimitive()
        || !json.get(Parser.OPERATOR).getAsJsonPrimitive().isString()) {
      throw new SyntaxException(
          "Type query/" + context + ", property 'operator'(string) must be set");
    }

    String operator = json.getAsJsonPrimitive(Parser.OPERATOR).getAsString();

    switch (operator) {
      case Filter.AND:
        return parseAnd(json);
      case Filter.OR:
        return parseOr(json);
      case Filter.NOT:
        return parseNot(json);
      case Filter.EQ:
        return parseEq(json);
      case Filter.NE:
        return parseNe(json);
      case Filter.GT:
        return parseGt(json);
      case Filter.LT:
        return parseLt(json);
      case Filter.GE:
        return parseGe(json);
      case Filter.LE:
        return parseLe(json);
      case Filter.IN:
        return parseIn(json);
      case Filter.REGEX:
        return parseRegex(json);
      default:
        throw new SyntaxException(
            "Type query/" + context + ", unsupported operator '" + operator + "'");
    }
  }
}
