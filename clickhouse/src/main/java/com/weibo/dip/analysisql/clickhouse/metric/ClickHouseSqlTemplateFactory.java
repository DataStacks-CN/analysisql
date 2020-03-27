package com.weibo.dip.analysisql.clickhouse.metric;

import com.weibo.dip.analysisql.metric.MySqlTemplate;
import com.weibo.dip.analysisql.metric.SqlTemplate;
import com.weibo.dip.analysisql.metric.SqlTemplateFactory;

/** ClickHouseSqlTemplateFactory. */
public class ClickHouseSqlTemplateFactory implements SqlTemplateFactory {
  @Override
  public SqlTemplate create() {
    return new MySqlTemplate(new ClickHouseSqlSnippetVisitor(new ClickHouseSqlFilterVisitor()));
  }
}
