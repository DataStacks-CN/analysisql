package com.weibo.dip.analysisql.dsl.request;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** QueryRequestVisitor. */
public abstract class QueryRequestVisitor {
  protected abstract void visitTopic(String topic);

  protected abstract void visitInterval(Interval interval);

  protected abstract void visitGranularity(Granularity granularity);

  protected abstract void visitMetric(String metric);

  protected abstract void visitWhere(Filter filter);

  protected abstract void visitGroups(String[] groups);

  protected abstract void visitHaving(Filter filter);

  protected abstract void visitOrders(Order[] orders);

  protected abstract void visitLimit(int limit);

  /**
   * Visit query request.
   *
   * @param request query request
   */
  public void visit(QueryRequest request) {
    visitTopic(request.getTopic());
    visitInterval(request.getInterval());
    visitGranularity(request.getGranularity());
    visitMetric(request.getMetric());
    visitWhere(request.getWhere());
    visitGroups(request.getGroups());
    visitHaving(request.getHaving());
    visitOrders(request.getOrders());
    visitLimit(request.getLimit());
  }
}
