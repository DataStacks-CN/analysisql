package com.weibo.dip.analysisql.clickhouse.metric;

import com.weibo.dip.analysisql.dsl.request.Granularity;
import com.weibo.dip.analysisql.metric.SqlFilterVisitor;
import com.weibo.dip.analysisql.metric.SqlSnippetVisitor;
import com.weibo.dip.analysisql.metric.SqlTemplate;

/** ClickHouseSqlSnippetVisitor. */
public class ClickHouseSqlSnippetVisitor extends SqlSnippetVisitor {

  public ClickHouseSqlSnippetVisitor(SqlFilterVisitor filterVisitor) {
    super(filterVisitor);
  }

  @Override
  protected void visitGranularity(Granularity granularity) {
    /*
     select:

     toStartOfInterval(fdate, INTERVAL ... ...) AS timeBucket
    */
    String unit;

    switch (granularity.getUnit()) {
      case s:
        unit = "SECOND";
        break;
      case m:
        unit = "MINUTE";
        break;
      case h:
        unit = "HOUR";
        break;
      case d:
        unit = "DAY";
        break;
      case w:
        unit = "WEEK";
        break;
      case M:
        unit = "MONTH";
        break;
      case q:
        unit = "QUARTER";
        break;
      case y:
        unit = "YEAR";
        break;
      default:
        throw new UnsupportedOperationException();
    }

    columns =
        String.format(
            "toStartOfInterval(fdate, INTERVAL %s %s) AS %s",
            granularity.getData(), unit, SqlTemplate.TIME_BUCKET);
  }
}
