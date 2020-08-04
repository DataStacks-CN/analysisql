package com.weibo.dip.analysisql.connector;

import com.weibo.dip.analysisql.metric.MetricCalculator;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Metadata. */
public abstract class Metadata implements Closeable {
  protected String topic;
  protected String alias;
  protected String desc;

  protected List<Dimension> dimensions;

  protected List<Metric> metrics;
  protected Map<String, MetricCalculator> calculators;

  /**
   * Initialize a instance with topic.
   *
   * @param topic topic
   */
  public Metadata(String topic) {
    this(topic, null, null);
  }

  /**
   * Initialize a instance with topic, alias, desc.
   *
   * @param topic topic
   * @param alias alias
   * @param desc description
   */
  public Metadata(String topic, String alias, String desc) {
    this.topic = topic;
    this.alias = alias;
    this.desc = desc;

    dimensions = new ArrayList<>();

    metrics = new ArrayList<>();
    calculators = new HashMap<>();
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public void addDimension(String dimension, String alias) {
    dimensions.add(new Dimension(dimension, alias, null));
  }

  public void addDimension(String dimension, String alias, String desc) {
    dimensions.add(new Dimension(dimension, alias, desc));
  }

  public boolean containDimension(String name) {
    return dimensions.stream().anyMatch(dimension -> dimension.getName().equals(name));
  }

  public List<Dimension> getDimensions() {
    return dimensions;
  }

  public abstract List<String> getDimensionValues(String dimension);

  public void addMetric(String metric, String alias) {
    metrics.add(new Metric(metric, alias, null));
  }

  public void addMetric(String metric, String alias, String desc) {
    metrics.add(new Metric(metric, alias, desc));
  }

  public boolean containMetric(String name) {
    return metrics.stream().anyMatch(metric -> metric.getName().equals(name))
        && calculators.containsKey(name);
  }

  public List<Metric> getMetrics() {
    return metrics;
  }

  public void addCalculator(String metric, MetricCalculator calculator) {
    calculators.put(metric, calculator);
  }

  public Map<String, MetricCalculator> getCalculators() {
    return calculators;
  }

  /**
   * Get calculator.
   *
   * @param metric metric
   * @return MetricCalculator instance
   */
  public MetricCalculator getCalculator(String metric) {
    return calculators.get(metric);
  }

  @Override
  public void close() throws IOException {}
}
