package com.weibo.dip.analysisql.metric;

import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.response.Row;
import java.util.List;

/** MetricCalculator. */
public interface MetricCalculator {
  List<Row> calculate(QueryRequest request) throws Exception;
}
