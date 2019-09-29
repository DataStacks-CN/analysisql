package com.weibo.dip.analysisql.dsl.filter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.weibo.dip.analysisql.Connector;
import com.weibo.dip.analysisql.dsl.Parser;
import com.weibo.dip.analysisql.dsl.filter.logical.AndFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.NotFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.OrFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.EqDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.EqLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.EqStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GeDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GeLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GeStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GtDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GtLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GtStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.InDoubleArrayRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.InLongArrayRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.InStringArrayRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LeDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LeLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LeStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LtDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LtLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LtStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.NeDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.NeLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.NeStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.RegexFilter;
import com.weibo.dip.analysisql.exception.SyntaxException;
import java.util.ArrayList;
import java.util.List;

/** @author yurun */
public class FilterParser {
  private String context;

  private Connector connector;

  public FilterParser(String context, Connector connector) {
    this.context = context;
    this.connector = connector;
  }

  private Filter parseAndOrOr(String operator, JsonObject json) {
    if (!json.has(Parser.FILTERS)
        || !json.get(Parser.FILTERS).isJsonArray()
        || !(json.get(Parser.FILTERS).getAsJsonArray().size() == 0)) {
      throw new SyntaxException(
          "Type query/" + context + "/" + operator + ", property 'filters'(array) must be set");
    }

    List<Filter> filters = new ArrayList<>();

    JsonArray filterArray = json.getAsJsonArray(Parser.FILTERS);

    for (int index = 0; index < filterArray.size(); index++) {
      filters.add(parse(filterArray.get(index)));
    }

    switch (context) {
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

  private Filter parseStringEqOrNeOrGtOrLtOrGeOrLe(
      String operator, String name, JsonPrimitive primitive) {
    String value = primitive.getAsString();

    switch (operator) {
      case Filter.EQ:
        return new EqStringRelationalFilter(name, value);
      case Filter.NE:
        return new NeStringRelationalFilter(name, value);
      case Filter.GT:
        return new GtStringRelationalFilter(name, value);
      case Filter.LT:
        return new LtStringRelationalFilter(name, value);
      case Filter.GE:
        return new GeStringRelationalFilter(name, value);
      case Filter.LE:
        return new LeStringRelationalFilter(name, value);
      default:
        return null;
    }
  }

  private Filter parseNumberEqOrNeOrGtOrLtOrGeOrLe(
      String operator, String name, JsonPrimitive primitive) {
    Number value = primitive.getAsNumber();

    boolean isLong =
        (value instanceof Byte)
            || (value instanceof Short)
            || (value instanceof Integer)
            || (value instanceof Long);

    switch (operator) {
      case Filter.EQ:
        return isLong
            ? new EqLongRelationalFilter(name, value.longValue())
            : new EqDoubleRelationalFilter(name, value.doubleValue());
      case Filter.NE:
        return isLong
            ? new NeLongRelationalFilter(name, value.longValue())
            : new NeDoubleRelationalFilter(name, value.doubleValue());
      case Filter.GT:
        return isLong
            ? new GtLongRelationalFilter(name, value.longValue())
            : new GtDoubleRelationalFilter(name, value.doubleValue());
      case Filter.LT:
        return isLong
            ? new LtLongRelationalFilter(name, value.longValue())
            : new LtDoubleRelationalFilter(name, value.doubleValue());
      case Filter.GE:
        return isLong
            ? new GeLongRelationalFilter(name, value.longValue())
            : new GeDoubleRelationalFilter(name, value.doubleValue());
      case Filter.LE:
        return isLong
            ? new LeLongRelationalFilter(name, value.longValue())
            : new LeDoubleRelationalFilter(name, value.doubleValue());
      default:
        return null;
    }
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

    return value.isString()
        ? parseStringEqOrNeOrGtOrLtOrGeOrLe(operator, name, value)
        : parseNumberEqOrNeOrGtOrLtOrGeOrLe(operator, name, value);
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

    return new InStringArrayRelationalFilter(name, values);
  }

  private Filter parseLongIn(String name, JsonArray array) {
    long[] values = new long[array.size()];

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

    return new InLongArrayRelationalFilter(name, values);
  }

  private Filter parseDoubleIn(String name, JsonArray array) {
    double[] values = new double[array.size()];

    for (int index = 0; index < array.size(); index++) {
      Number value = array.get(index).getAsNumber();
      boolean isDouble = (value instanceof Float) || (value instanceof Double);
      if (!isDouble) {
        throw new SyntaxException(
            "Type query/" + context + "/in, property 'values'(array)'s type must be double");
      }

      values[index] = value.doubleValue();
    }

    return new InDoubleArrayRelationalFilter(name, values);
  }

  private Filter parseNumberIn(String name, JsonArray array) {
    Number value = array.get(0).getAsNumber();

    boolean isLong =
        (value instanceof Byte)
            || (value instanceof Short)
            || (value instanceof Integer)
            || (value instanceof Long);

    return isLong ? parseLongIn(name, array) : parseDoubleIn(name, array);
  }

  private Filter parseIn(JsonObject json) {
    if (!json.has(Parser.NAME)
        || !json.get(Parser.NAME).isJsonPrimitive()
        || !json.getAsJsonPrimitive(Parser.NAME).isString()
        || !json.has(Parser.VALUES)
        || !json.get(Parser.VALUES).isJsonArray()
        || !(json.get(Parser.VALUES).getAsJsonArray().size() == 0)) {
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

    boolean isString = valueArray.get(0).getAsJsonPrimitive().isString();

    return isString ? parseStringIn(name, valueArray) : parseNumberIn(name, valueArray);
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
    String pattern = json.getAsJsonPrimitive(Parser.VALUE).getAsString();

    return new RegexFilter(name, pattern);
  }

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
      case Filter.OR:
        return parseAndOrOr(operator, json);
      case Filter.NOT:
        return parseNot(json);
      case Filter.EQ:
      case Filter.NE:
      case Filter.GT:
      case Filter.LT:
      case Filter.GE:
      case Filter.LE:
        return parseEqOrNeOrGtOrLtOrGeOrLe(operator, json);
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
