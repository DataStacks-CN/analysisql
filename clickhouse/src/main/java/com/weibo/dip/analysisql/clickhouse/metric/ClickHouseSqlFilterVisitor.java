package com.weibo.dip.analysisql.clickhouse.metric;

import com.weibo.dip.analysisql.dsl.filter.RegexFilter;
import com.weibo.dip.analysisql.metric.SqlFilterVisitor;

/** ClickHouseSqlFilterVisitor. */
public class ClickHouseSqlFilterVisitor extends SqlFilterVisitor {
  @Override
  public String visitRegex(RegexFilter filter) {
    return String.format("match(%s, '%s') = 1", filter.getName(), filter.getPattern());
  }
}
