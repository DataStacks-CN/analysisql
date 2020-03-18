package com.weibo.dip.analysis.view;

import com.weibo.dip.analysisql.connector.Metadata;
import com.weibo.dip.analysisql.metric.MetricCalculator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    this(topic, null, null);
  }

  /**
   * Initialize a instance with topic, alias, desc.
   *
   * @param topic topic
   * @param alias alias
   * @param desc description
   */
  public View(String topic, String alias, String desc) {
    super(topic, alias, desc);

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
  public Map<String, MetricCalculator> getCalculators() {
    throw new UnsupportedOperationException();
  }

  public void addTable(Table table) {
    tables.add(table);
  }

  @Override
  public MetricCalculator getCalculator(String metric) {
    return router;
  }

  /**
   * Get tables using dimension name.
   *
   * @param name dimension name
   * @return tables
   */
  public List<Table> getTableUsingDimension(String name) {
    return tables.stream()
        .filter(table -> table.containDimension(name))
        .collect(Collectors.toList());
  }

  /**
   * Get tables using metric name.
   *
   * @param name metric name
   * @return tables
   */
  public List<Table> getTableUsingMetric(String name) {
    return tables.stream().filter(table -> table.containMetric(name)).collect(Collectors.toList());
  }
}
