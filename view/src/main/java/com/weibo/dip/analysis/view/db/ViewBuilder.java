package com.weibo.dip.analysis.view.db;

import com.weibo.dip.analysis.view.DefaultView;
import com.weibo.dip.analysis.view.Table;
import com.weibo.dip.analysisql.clickhouse.metric.ClickHouseCalculator;
import com.weibo.dip.analysisql.clickhouse.metric.ClickHouseSqlTemplateFactory;
import com.weibo.dip.analysisql.dsl.request.Granularity;
import com.weibo.dip.analysisql.metric.MetricCalculator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** ViewBuilder. */
public class ViewBuilder {
  public static final String TYPE_CLICKHOUSE = "clickhouse";
  public static final String TYPE_MYSQL = "mysql";
  public static final String TYPE_PRESTO = "presto";

  private DefaultView view = null;

  public ViewBuilder() {}

  public String getTopic() {
    return view.getTopic();
  }

  public List<String> getTables() {
    return view.getTables().stream().map(Table::getName).collect(Collectors.toList());
  }

  /**
   * Set view topic.
   *
   * @param topic topic
   * @return builder
   */
  public ViewBuilder topic(String topic) {
    if (Objects.isNull(view)) {
      view = new DefaultView(topic);
    }

    return this;
  }

  /**
   * Set view alias.
   *
   * @param alias alias
   * @return builder
   */
  public ViewBuilder alias(String alias) {
    view.setAlias(alias);

    return this;
  }

  /**
   * Set view desc.
   *
   * @param desc desc
   * @return builder
   */
  public ViewBuilder desc(String desc) {
    view.setDesc(desc);

    return this;
  }

  /**
   * Add view dimension.
   *
   * @param name dimension name
   * @param alias dimension alias
   * @param desc dimension desc
   * @return builder
   */
  public ViewBuilder dimension(String name, String alias, String desc) {
    view.addDimension(name, alias, desc);

    return this;
  }

  /**
   * Add view metric.
   *
   * @param name metric name
   * @param alias metric alias
   * @param desc metric desc
   * @return builder
   */
  public ViewBuilder metric(String name, String alias, String desc) {
    view.addMetric(name, alias, desc);

    return this;
  }

  /**
   * Add view table.
   *
   * @param name table name
   * @param data table granularity/data
   * @param unit table granularity/unit
   * @param period table period
   * @param delay table delay
   * @return builder
   */
  public ViewBuilder table(String name, int data, String unit, int period, int delay) {
    view.addTable(
        new Table(
            view, name, new Granularity(data, Granularity.Unit.valueOf(unit)), period, delay));

    return this;
  }

  /**
   * Add view table dimension.
   *
   * @param table table name
   * @param name dimension name
   * @return builder
   */
  public ViewBuilder tableDimension(String table, String name) {
    view.getTable(table).addDimension(name);

    return this;
  }

  /**
   * Add view table calculator.
   *
   * @param table table name
   * @param name metric name
   * @param type db type
   * @param url db url
   * @param user db user
   * @param passwd db passwd
   * @param sql query sql
   * @return builder
   */
  public ViewBuilder tableCalculator(
      String table, String name, String type, String url, String user, String passwd, String sql) {
    MetricCalculator calculator;

    switch (type) {
      case TYPE_CLICKHOUSE:
        break;
      case TYPE_MYSQL:
        break;
      case TYPE_PRESTO:
        break;
      default:
        throw new UnsupportedOperationException();
    }

    return this;
  }

  public DefaultView build() {
    return view;
  }
}
