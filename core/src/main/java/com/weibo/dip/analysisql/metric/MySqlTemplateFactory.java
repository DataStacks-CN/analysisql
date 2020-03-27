package com.weibo.dip.analysisql.metric;

/** MySqlTemplateFactory. */
public class MySqlTemplateFactory implements SqlTemplateFactory {
  @Override
  public SqlTemplate create() {
    return new MySqlTemplate(new SqlSnippetVisitor(new SqlFilterVisitor()));
  }
}
