package com.weibo.dip.analysisql.metric;

import com.weibo.dip.analysisql.dsl.request.QueryRequest;

/** SqlTemplate. */
public abstract class SqlTemplate {
  public static final String TIME_BUCKET = "timeBucket";

  public abstract String render(String sql, QueryRequest request) throws Exception;
}
