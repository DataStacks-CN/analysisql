package com.weibo.dip.analysis.view;

import com.weibo.dip.analysisql.connector.Metadata;
import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.request.Granularity;
import com.weibo.dip.analysisql.dsl.request.Interval;
import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.metric.MetricCalculator;
import com.weibo.dip.analysisql.util.GsonCreator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javafx.util.Pair;
import org.apache.commons.lang3.ArrayUtils;

/** Table. */
public class Table extends Metadata implements Comparable<Table> {
  private String name;
  private Granularity granularity;
  private int period;
  private int delay;

  public Table(View view) {
    super(view.getTopic(), view.getAlias(), view.getDesc());
  }

  /**
   * Initialize a instance with view, granularity, period and delay.
   *
   * @param view view
   * @param name table name
   * @param granularity table granularity
   * @param period table period
   * @param delay table delay
   */
  public Table(View view, String name, Granularity granularity, int period, int delay) {
    this(view);

    this.name = name;
    this.granularity = granularity;
    this.period = period;
    this.delay = delay;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Granularity getGranularity() {
    return granularity;
  }

  public void setGranularity(Granularity granularity) {
    this.granularity = granularity;
  }

  public int getPeriod() {
    return period;
  }

  public void setPeriod(int period) {
    this.period = period;
  }

  public int getDelay() {
    return delay;
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }

  @Override
  public void addDimension(String dimension, String alias) {
    throw new UnsupportedOperationException();
  }

  public void addDimension(String dimension) {
    dimensions.add(new Pair<>(dimension, dimension));
  }

  @Override
  public List<String> getDimensionValues(String dimension) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addMetric(String metric, String alias) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addCalculator(String metric, MetricCalculator calculator) {
    super.addCalculator(metric, calculator);

    metrics.add(new Pair<>(metric, metric));
  }

  private boolean satisfyMetric(String metric) {
    return metrics.stream().anyMatch(pair -> pair.getKey().equals(metric));
  }

  private boolean satisfyDimensions(Filter where, String[] groups) {
    Set<String> dimensions = new HashSet<>();

    if (Objects.nonNull(where)) {
      dimensions.addAll(new FilterDimensionVisitor().visit(where));
    }

    if (ArrayUtils.isNotEmpty(groups)) {
      dimensions.addAll(Arrays.asList(groups));
    }

    return dimensions.stream()
        .allMatch(dimension -> this.dimensions.contains(new Pair<>(dimension, dimension)));
  }

  private boolean satisfyGranularity(Granularity granularity) {
    return this.granularity.getMilliseconds() <= granularity.getMilliseconds();
  }

  private boolean satisfyInterval(Interval interval) {
    long g = granularity.getMilliseconds();

    long p = period * g;
    long d = delay * g;

    long end = System.currentTimeMillis();
    long start = end - p;

    start = (start % g == 0) ? start : ((start / g + 1) * g);
    end = (end - d) / g * g;

    return !(interval.getEnd().getTime() < start || interval.getStart().getTime() > end);
  }

  /**
   * Check if the table meets the conditions.
   *
   * @param request query request
   * @return true if meet, else false
   */
  public boolean satisfy(QueryRequest request) {
    return satisfyMetric(request.getMetric())
        && satisfyDimensions(request.getWhere(), request.getGroups())
        && satisfyGranularity(request.getGranularity())
        && satisfyInterval(request.getInterval());
  }

  @Override
  public int compareTo(Table table) {
    int compare =
        Long.compare(this.granularity.getMilliseconds(), table.granularity.getMilliseconds());
    if (compare != 0) {
      return -compare;
    }

    compare = Integer.compare(this.dimensions.size(), table.dimensions.size());

    return compare;
  }

  @Override
  public String toString() {
    return GsonCreator.create().toJson(this);
  }
}
