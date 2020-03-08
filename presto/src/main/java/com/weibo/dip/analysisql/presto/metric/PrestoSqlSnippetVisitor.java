package com.weibo.dip.analysisql.presto.metric;

import com.weibo.dip.analysisql.dsl.Parser;
import com.weibo.dip.analysisql.dsl.request.Granularity;
import com.weibo.dip.analysisql.dsl.request.Interval;
import com.weibo.dip.analysisql.metric.SqlFilterVisitor;
import com.weibo.dip.analysisql.metric.SqlSnippetVisitor;
import com.weibo.dip.analysisql.metric.SqlTemplate;

/** PrestoSqlSnippetVisitor. */
public class PrestoSqlSnippetVisitor extends SqlSnippetVisitor {

  public PrestoSqlSnippetVisitor(SqlFilterVisitor filterVisitor) {
    super(filterVisitor);
  }

  @Override
  protected void visitGranularity(Granularity granularity) {
    long gap = granularity.getMilliseconds() / 1000;
    assert gap > 0;

    /*
     select:

     FLOOR(
       TO_UNIXTIME(
         DATE_ADD(
           'second',
           date_diff('%s', parse_datetime('%s', '%s'), parse_datetime(fdate, '%s')) / %s * %s,
           parse_datetime('%s', '%s')
         )
       )
     ) AS timeBucket
    */
    String format =
        "FLOOR"
            + "("
            + "TO_UNIXTIME"
            + "("
            + "DATE_ADD"
            + "("
            + "'%s'"
            + ", "
            + "date_diff('%s', parse_datetime('%s', '%s'), parse_datetime(fdate, '%s')) / %s * %s"
            + ", "
            + "parse_datetime('%s', '%s')"
            + ")"
            + ")"
            + ") AS %s";

    columns =
        String.format(
            format,
            "second",
            "second",
            "1970-01-01 00:00:00",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss",
            gap,
            gap,
            "1970-01-01 00:00:00",
            "yyyy-MM-dd HH:mm:ss",
            SqlTemplate.TIME_BUCKET);
  }

  @Override
  protected void visitInterval(Interval interval) {
    /*
     where:

     CONCAT(fday, ' ', fhour, ':00:00') BETWEEN '...' AND '...' AND fdate BETWEEN '...' AND '...'
    */
    String start = Parser.DATETIME_FORMAT.format(interval.getStart());
    String end = Parser.DATETIME_FORMAT.format(interval.getEnd());

    where =
        String.format(
            "CONCAT(fday, ' ', fhour, ':00:00') BETWEEN '%s' AND '%s' "
                + "AND fdate BETWEEN '%s' AND '%s'",
            start, end, start, end);
  }
}
