package com.weibo.dip.analysisql.connector;

import com.weibo.dip.analysisql.metric.MetricCalculator;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/** Metadata. */
public abstract class Metadata {
  protected String topic;
  protected String alias;
  protected String desc;

  protected List<Dimension> dimensions;

  protected List<Pair<String, String>> metrics;
  protected List<Pair<String, MetricCalculator>> calculators;

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
    calculators = new ArrayList<>();
  }

  public String getTopic() {
    return topic;
  }

  public String getAlias() {
    return alias;
  }

  public String getDesc() {
    return desc;
  }

  public void addDimension(String dimension, String alias) {
    dimensions.add(new Dimension(dimension, alias, null));
  }

  public void addDimension(String dimension, String alias, String desc) {
    dimensions.add(new Dimension(dimension, alias, desc));
  }

  public List<Dimension> getDimensions() {
    return dimensions;
  }

  public abstract List<String> getDimensionValues(String dimension);

  public void addMetric(String metric, String alias) {
    metrics.add(new Pair<>(metric, alias));
  }

  public List<Pair<String, String>> getMetrics() {
    return metrics;
  }

  public void addCalculator(String metric, MetricCalculator calculator) {
    calculators.add(new Pair<>(metric, calculator));
  }

  public List<Pair<String, MetricCalculator>> getCalculators() {
    return calculators;
  }

  /**
   * Get calculator.
   *
   * @param metric metric
   * @return MetricCalculator instance
   */
  public MetricCalculator getCalculator(String metric) {
    MetricCalculator calculator = null;

    for (Pair<String, MetricCalculator> pair : calculators) {
      if (pair.getKey().equals(metric)) {
        calculator = pair.getValue();
        break;
      }
    }

    return calculator;
  }
}
