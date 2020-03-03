package com.weibo.dip.analysis.view;

import com.weibo.dip.analysisql.connector.Metadata;
import com.weibo.dip.analysisql.metric.MetricCalculator;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/** View. */
public abstract class View extends Metadata {
  protected List<Table> tables;

  protected PolicyRouter router;

  /**
   * Initialize a instance with topic.
   *
   * @param topic topic
   */
  public View(String topic) {
    super(topic);

    tables = new ArrayList<>();

    router = new PolicyRouter(this);
  }

  public List<Table> getTables() {
    return tables;
  }

  public PolicyRouter getRouter() {
    return router;
  }

  @Override
  public void addCalculator(String metric, MetricCalculator calculator) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Pair<String, MetricCalculator>> getCalculators() {
    throw new UnsupportedOperationException();
  }

  public void addTable(Table table) {
    tables.add(table);
  }

  @Override
  public MetricCalculator getCalculator(String metric) {
    return router;
  }
}
