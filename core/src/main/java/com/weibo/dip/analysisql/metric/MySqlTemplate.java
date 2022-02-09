package com.weibo.dip.analysisql.metric;

import com.weibo.dip.analysisql.dsl.request.QueryRequest;

/**
 * MySqlTemplate.
 */
public class MySqlTemplate extends SqlTemplate {
  private final SqlSnippetVisitor snippetVisitor;

  public MySqlTemplate(SqlSnippetVisitor snippetVisitor) {
    this.snippetVisitor = snippetVisitor;
  }

  @Override
  public String render(String sql, QueryRequest request) throws Exception {
    snippetVisitor.visit(request);

    return sql
            .replace("$COLUMNS", snippetVisitor.getColumns())
            .replace("$METRIC", snippetVisitor.getMetric())
            .replace("$GROUPS", snippetVisitor.getGroups())
            .replace("$WHERE", snippetVisitor.getWhere())
            .replace("$HAVING", snippetVisitor.getHaving())
            .replace("$ORDERS", snippetVisitor.getOrders())
            .replace("$LIMIT", snippetVisitor.getLimit())
            .replace("$GAP", String.valueOf(snippetVisitor.getGap()));
  }
}
