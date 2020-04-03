package com.weibo.dip.analysisql.metric;

import com.weibo.dip.analysisql.dsl.Parser;
import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.request.Granularity;
import com.weibo.dip.analysisql.dsl.request.Interval;
import com.weibo.dip.analysisql.dsl.request.Order;
import com.weibo.dip.analysisql.dsl.request.QueryRequestVisitor;
import java.util.Objects;
import java.util.StringJoiner;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/** SqlSnippetVisitor. */
public class SqlSnippetVisitor extends QueryRequestVisitor {
  protected SqlFilterVisitor filterVisitor;

  protected String columns;
  protected String metric;
  protected String groups;
  protected String where;
  protected String having;
  protected String orders;
  protected String limit;

  protected long gap;

  public SqlSnippetVisitor(SqlFilterVisitor filterVisitor) {
    this.filterVisitor = filterVisitor;
  }

  public String getColumns() {
    return columns;
  }

  public String getMetric() {
    return metric;
  }

  public String getGroups() {
    return groups;
  }

  public String getWhere() {
    return where;
  }

  public String getHaving() {
    return having;
  }

  public String getOrders() {
    return orders;
  }

  public String getLimit() {
    return limit;
  }

  public long getGap() {
    return gap;
  }

  @Override
  protected void visitTopic(String topic) {}

  @Override
  protected void visitInterval(Interval interval) {
    /*
     where: fdate BETWEEN '...' AND '...'
    */
    where =
        String.format(
            "fdate BETWEEN '%s' AND '%s'",
            Parser.DATETIME_FORMAT.format(interval.getStart()),
            Parser.DATETIME_FORMAT.format(interval.getEnd()));
  }

  @Override
  protected void visitGranularity(Granularity granularity) {
    gap = granularity.getMilliseconds() / 1000;
    assert gap > 0;

    /*
     select: TIMESTAMPADD(
                SECOND,
                FLOOR(TIMESTAMPDIFF(SECOND, '1970-01-01 00:00:00', fdate) / $(gap)) * $(gap),
                '1970-01-01 00:00:00'
              ) AS timeBucket
    */
    columns =
        String.format(
            "TIMESTAMPADD"
                + "("
                + "SECOND, "
                + "FLOOR(TIMESTAMPDIFF(SECOND, '%s', fdate) / %s) * %s, "
                + "'%s'"
                + ") AS %s",
            "1970-01-01 00:00:00", gap, gap, "1970-01-01 00:00:00", SqlTemplate.TIME_BUCKET);
  }

  @Override
  protected void visitMetric(String metric) {
    this.metric = metric;
  }

  @Override
  protected void visitWhere(Filter filter) {
    if (Objects.isNull(filter)) {
      return;
    }

    /*
     where: fdate BETWEEN '...' AND '...' AND ...
    */
    where = where + " AND " + filterVisitor.visit(filter);
  }

  @Override
  protected void visitGroups(String[] groups) {
    /*
     group by: timeBucket
    */
    this.groups = SqlTemplate.TIME_BUCKET;

    if (ArrayUtils.isEmpty(groups)) {
      return;
    }

    /*
     select: TIMESTAMPADD(
        SECOND,
        FLOOR(TIMESTAMPDIFF(SECOND, '1970-01-01 00:00:00', fdate) / $(gap)) * $(gap),
        '1970-01-01 00:00:00'
      ) AS timeBucket, ...
    */
    StringJoiner columnSnippet = new StringJoiner(", ");

    columnSnippet.add(columns);

    /*
     group by: timeBucket, ...
    */
    StringJoiner groupSnippet = new StringJoiner(", ");

    groupSnippet.add(this.groups);

    for (String group : groups) {
      columnSnippet.add(group);
      groupSnippet.add(group);
    }

    this.columns = columnSnippet.toString();
    this.groups = groupSnippet.toString();
  }

  @Override
  protected void visitHaving(Filter filter) {
    /*
      having: timeBucket IS NOT NULL
    */
    having = "timeBucket IS NOT NULL";

    if (Objects.isNull(filter)) {
      return;
    }

    /*
      having: timeBucket IS NOT NULL AND ...
    */
    having = having + " AND " + filterVisitor.visit(filter);
  }

  @Override
  protected void visitOrders(Order[] orders) {
    /*
     order by: timeBucket ASC
    */
    this.orders = "timeBucket ASC";

    if (ArrayUtils.isEmpty(orders)) {
      return;
    }

    /*
     order by: timeBucket ASC, ...
    */
    StringJoiner snippet = new StringJoiner(", ");

    for (Order order : orders) {
      snippet.add(order.getName() + StringUtils.SPACE + order.getSort().name().toUpperCase());
    }

    this.orders = snippet.toString();
  }

  @Override
  protected void visitLimit(int limit) {
    this.limit = String.valueOf(limit);
  }
}
