package com.weibo.dip.analysis.view;

import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.metric.MetricCalculator;
import com.weibo.dip.analysisql.response.Row;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Policy router.
 */
public class PolicyRouter implements MetricCalculator {
  private static final Logger LOGGER = LoggerFactory.getLogger(PolicyRouter.class);

  private final View view;

  public PolicyRouter(View view) {
    this.view = view;
  }

  @Override
  public List<Row> calculate(QueryRequest request) throws Exception {
    List<Table> tables = view.getTables();
    if (CollectionUtils.isEmpty(tables)) {
      LOGGER.warn("View {} does not have any registered table!", view.getTopic());
      return null;
    }

    tables =
            tables.stream()
                    // filter
                    .filter(table -> table.satisfy(request))
                    // sort
                    .sorted()
                    .collect(Collectors.toList());
    if (CollectionUtils.isEmpty(tables)) {
      LOGGER.warn(
              "View {} does not have any satisfied table for request {}", view.getTopic(), request);
      return null;
    }

    Table table = tables.get(0);
    LOGGER.info("request: {}, route table: {}", request.getSessionId(), table.getName());

    return table.getCalculator(request.getMetric()).calculate(request);
  }
}
