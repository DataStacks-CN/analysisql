package com.weibo.dip.analysisql.presto.metric;

import com.weibo.dip.analysisql.metric.SqlTemplate;
import com.weibo.dip.analysisql.metric.SqlTemplateFactory;

/** PrestoSqlTemplateFactory. */
public class PrestoSqlTemplateFactory implements SqlTemplateFactory {
  @Override
  public SqlTemplate create() {
    return new PrestoSqlTemplate(new PrestoSqlSnippetVisitor(new PrestoSqlFilterVisitor()));
  }
}
