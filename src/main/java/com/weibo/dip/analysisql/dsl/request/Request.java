package com.weibo.dip.analysisql.dsl.request;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Request. */
public abstract class Request implements Serializable {
  public static final String GET_TOPICS = "getTopics";
  public static final String GET_DIMENTIONS = "getDimentions";
  public static final String GET_DIMENTION_VALUES = "getDimentionValues";
  public static final String GET_METRICS = "getMetrics";
  public static final String QUERY = "query";

  protected String type;

  public Request() {}

  public Request(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    GsonBuilder builder = new GsonBuilder();

    builder.setPrettyPrinting();

    builder.registerTypeAdapter(new TypeToken<Date>() {}.getType(), new DateSerializer());

    return builder.create().toJson(this);
  }

  public static class DateSerializer implements JsonSerializer<Date> {
    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {

      return new JsonPrimitive(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(src));
    }
  }
}
