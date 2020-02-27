package com.weibo.dip.analysisql.connector;

import com.weibo.dip.analysisql.metric.MetricCalculator;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/** Metadata. */
public abstract class Metadata {
  protected String topic;
  protected List<Pair<String, String>> dimentions;
  protected List<Pair<String, String>> metrics;
  protected List<Pair<String, MetricCalculator>> calculators;

  /**
   * Initializes a instance with topic.
   *
   * @param topic topic
   */
  public Metadata(String topic) {
    this.topic = topic;

    dimentions = new ArrayList<>();
    metrics = new ArrayList<>();
    calculators = new ArrayList<>();
  }

  public String getTopic() {
    return topic;
  }

  public void addDimention(String dimention, String alias) {
    dimentions.add(new Pair<>(dimention, alias));
  }

  public List<Pair<String, String>> getDimentions() {
    return dimentions;
  }

  public abstract List<String> getDimentionValues(String dimention);

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
