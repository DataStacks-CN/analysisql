package com.weibo.dip.analysisql.metric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** MetricRegistry. */
public class MetricRegistry {
  private static final Map<String, Map<String, Class<? extends MetricCalculator>>> registry =
      new HashMap<>();

  /**
   * Register a metric calculator.
   *
   * @param topic topic
   * @param metric metric
   * @param clazz calculator
   */
  public static void register(
      String topic, String metric, Class<? extends MetricCalculator> clazz) {
    if (!registry.containsKey(topic)) {
      registry.put(topic, new HashMap<>());
    }

    registry.get(topic).put(metric, clazz);
  }

  public static List<String> getTopics() {
    return new ArrayList<>(registry.keySet());
  }

  public static List<String> getMetrics(String topic) {
    return registry.containsKey(topic) ? new ArrayList<>(registry.get(topic).keySet()) : null;
  }

  public static Class<? extends MetricCalculator> getCalculator(String topic, String metric) {
    return registry.containsKey(topic) ? registry.get(topic).get(metric) : null;
  }
}
