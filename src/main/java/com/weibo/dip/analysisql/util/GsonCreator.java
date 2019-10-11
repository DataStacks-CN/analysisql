package com.weibo.dip.analysisql.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/** GsonCreator. */
public class GsonCreator {
  private static final Gson GSON;

  static {
    GsonBuilder builder = new GsonBuilder();

    builder
        .setPrettyPrinting()
        .serializeNulls()
        .serializeSpecialFloatingPointValues()
        .disableHtmlEscaping();

    builder.registerTypeAdapter(new TypeToken<Date>() {}.getType(), new DateSerializer());

    GSON = builder.create();
  }

  public static Gson create() {
    return GSON;
  }

  public static class DateSerializer implements JsonSerializer<Date> {
    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {

      return new JsonPrimitive(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(src));
    }
  }
}
