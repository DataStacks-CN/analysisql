package com.weibo.dip.analysisql.metric;

import com.weibo.dip.analysisql.dsl.request.QueryRequest;

/** MySqlTemplate. */
public class MySqlTemplate extends SqlTemplate {
  private SqlSnippetVisitor snippetVisitor;

  public MySqlTemplate(SqlSnippetVisitor snippetVisitor) {
    this.snippetVisitor = snippetVisitor;
  }

  @Override
  public String render(String resource, QueryRequest request) throws Exception {
    snippetVisitor.visit(request);

    return load(resource)
        .replace("$COLUMNS", snippetVisitor.getColumns())
        .replace("$METRIC", snippetVisitor.getMetric())
        .replace("$GROUPS", snippetVisitor.getGroups())
        .replace("$WHERE", snippetVisitor.getWhere())
        .replace("$HAVING", snippetVisitor.getHaving())
        .replace("$ORDERS", snippetVisitor.getOrders())
        .replace("$LIMIT", snippetVisitor.getLimit());
  }
}
