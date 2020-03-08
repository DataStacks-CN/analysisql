package com.weibo.dip.analysisql.presto.metric;

import com.weibo.dip.analysisql.dsl.filter.RegexFilter;
import com.weibo.dip.analysisql.metric.SqlFilterVisitor;

/** PrestoSqlFilterVisitor. */
public class PrestoSqlFilterVisitor extends SqlFilterVisitor {
  @Override
  public String visitRegex(RegexFilter filter) {
    return String.format("REGEXP_LIKE(%s, %s) ", filter.getName(), filter.getPattern());
  }
}
