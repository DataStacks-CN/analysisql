package com.weibo.dip.analysis.view.dynamic;

import com.weibo.dip.analysis.view.DefaultView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** ViewLoader. */
public class ViewLoader {
  private static final Logger LOGGER = LoggerFactory.getLogger(ViewLoader.class);

  public static final String TABLE_VIEW_INFO = "aql_view_info";
  public static final String TABLE_VIEW_DIMENSION = "aql_view_dimension";
  public static final String TABLE_VIEW_DIMENSION_VALUE = "aql_view_dimension_value";
  public static final String TABLE_VIEW_METRIC = "aql_view_metric";
  public static final String TABLE_VIEW_TABLE_INFO = "aql_view_table_info";
  public static final String TABLE_VIEW_TABLE_DIMENSION = "aql_view_table_dimension";
  public static final String TABLE_VIEW_TABLE_CALCULATOR = "aql_view_table_calculator";

  private String url;
  private String user;
  private String passwd;

  private String viewInfo = TABLE_VIEW_INFO;
  private String viewDimension = TABLE_VIEW_DIMENSION;
  private String viewDimensionValue = TABLE_VIEW_DIMENSION_VALUE;
  private String viewMetric = TABLE_VIEW_METRIC;
  private String viewTableInfo = TABLE_VIEW_TABLE_INFO;
  private String viewTableDimension = TABLE_VIEW_TABLE_DIMENSION;
  private String viewTableCalculator = TABLE_VIEW_TABLE_CALCULATOR;

  public ViewLoader() {}

  /**
   * Initialize a instance with db url, user and passwd.
   *
   * @param url jdbc url
   * @param user db user
   * @param passwd db passwd
   */
  public ViewLoader(String url, String user, String passwd) {
    this.url = url;
    this.user = user;
    this.passwd = passwd;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getViewInfo() {
    return viewInfo;
  }

  public void setViewInfo(String viewInfo) {
    this.viewInfo = viewInfo;
  }

  public String getViewDimension() {
    return viewDimension;
  }

  public void setViewDimension(String viewDimension) {
    this.viewDimension = viewDimension;
  }

  public String getViewDimensionValue() {
    return viewDimensionValue;
  }

  public void setViewDimensionValue(String viewDimensionValue) {
    this.viewDimensionValue = viewDimensionValue;
  }

  public String getViewMetric() {
    return viewMetric;
  }

  public void setViewMetric(String viewMetric) {
    this.viewMetric = viewMetric;
  }

  public String getViewTableInfo() {
    return viewTableInfo;
  }

  public void setViewTableInfo(String viewTableInfo) {
    this.viewTableInfo = viewTableInfo;
  }

  public String getViewTableDimension() {
    return viewTableDimension;
  }

  public void setViewTableDimension(String viewTableDimension) {
    this.viewTableDimension = viewTableDimension;
  }

  public String getViewTableCalculator() {
    return viewTableCalculator;
  }

  public void setViewTableCalculator(String viewTableCalculator) {
    this.viewTableCalculator = viewTableCalculator;
  }

  private List<ViewBuilder> fetchViewBuilders() throws Exception {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      conn = DriverManager.getConnection(url, user, passwd);
      stmt = conn.createStatement();
      rs =
          stmt.executeQuery(
              String.format(
                  "SELECT avi_topic, avi_alias, avi_desc, avi_state FROM %s WHERE state = 1",
                  viewInfo));

      List<ViewBuilder> builders = new ArrayList<>();

      while (rs.next()) {
        ViewBuilder builder = new ViewBuilder(this);

        builder
            .topic(rs.getString("avi_topic"))
            .alias(rs.getString("avi_alias"))
            .desc(rs.getString("avi_desc"));

        builders.add(builder);
      }

      return builders;
    } finally {
      closeQuietly(conn, stmt, rs);
    }
  }

  private void buildViewDimensions(ViewBuilder builder) throws Exception {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      conn = DriverManager.getConnection(url, user, passwd);
      stmt = conn.createStatement();
      rs =
          stmt.executeQuery(
              String.format(
                  "SELECT avd_name, avd_alias, avd_desc FROM %s WHERE avd_topic = %s",
                  viewDimension, builder.getTopic()));

      while (rs.next()) {
        String name = rs.getString("avd_name");
        String alias = rs.getString("avd_alias");
        String desc = rs.getString("avd_desc");

        builder.dimension(name, alias, desc);
        LOGGER.info(
            "View[{}] build dimension: name[{}], alias[{}], desc[{}]",
            builder.getTopic(),
            name,
            alias,
            desc);
      }

    } finally {
      closeQuietly(conn, stmt, rs);
    }
  }

  private void buildViewMetrics(ViewBuilder builder) throws Exception {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      conn = DriverManager.getConnection(url, user, passwd);
      stmt = conn.createStatement();
      rs =
          stmt.executeQuery(
              String.format(
                  "SELECT avm_name, avm_alias, avm_desc FROM %s WHERE avm_topic = %s",
                  viewMetric, builder.getTopic()));

      while (rs.next()) {
        String name = rs.getString("avm_name");
        String alias = rs.getString("avm_alias");
        String desc = rs.getString("avd_desc");

        builder.metric(name, alias, desc);
        LOGGER.info(
            "View[{}] build metric: name[{}], alias[{}], desc[{}]",
            builder.getTopic(),
            name,
            alias,
            desc);
      }

    } finally {
      closeQuietly(conn, stmt, rs);
    }
  }

  private void buildViewTables(ViewBuilder builder) throws Exception {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      conn = DriverManager.getConnection(url, user, passwd);
      stmt = conn.createStatement();
      rs =
          stmt.executeQuery(
              String.format(
                  "SELECT "
                      + "avti_name, avti_data, avti_unit, avti_period, avti_delay "
                      + "FROM %s WHERE avti_topic = %s AND avti_state = 1",
                  viewTableInfo, builder.getTopic()));

      while (rs.next()) {
        String name = rs.getString("avti_name");
        int data = rs.getInt("avti_data");
        String unit = rs.getString("avti_unit");
        int period = rs.getInt("avti_period");
        int delay = rs.getInt("avti_delay");

        builder.table(name, data, unit, period, delay);
        LOGGER.info(
            "View[{}] build table: name[{}], data[{}], unit[{}], period[{}], delay[{}]",
            builder.getTopic(),
            name,
            data,
            unit,
            period,
            delay);
      }
    } finally {
      closeQuietly(conn, stmt, rs);
    }
  }

  private void buildViewTableDimensions(ViewBuilder builder, String table) throws Exception {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      conn = DriverManager.getConnection(url, user, passwd);
      stmt = conn.createStatement();
      rs =
          stmt.executeQuery(
              String.format(
                  "SELECT avtd_name FROM %s WHERE avtd_topic = %s AND avtd_table = %s",
                  viewTableDimension, builder.getTopic(), table));

      while (rs.next()) {
        String name = rs.getString("avtd_name");

        builder.tableDimension(table, name);
        LOGGER.info(
            "View[{}]/Table[{}] build dimension: name[{}]", builder.getTopic(), table, name);
      }
    } finally {
      closeQuietly(conn, stmt, rs);
    }
  }

  private void buildViewTableCalculators(ViewBuilder builder, String table) throws Exception {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      conn = DriverManager.getConnection(url, user, passwd);
      stmt = conn.createStatement();
      rs =
          stmt.executeQuery(
              String.format(
                  "SELECT "
                      + "avtc_metric, avtc_type, avtc_url, avtc_user, avtc_passwd, avtc_sql "
                      + "FROM %s WHERE avtc_topic = %s AND avtc_table = %s",
                  viewTableCalculator, builder.getTopic(), table));

      while (rs.next()) {
        String name = rs.getString("avtc_metric");
        String type = rs.getString("avtc_type");
        String url = rs.getString("avtc_url");
        String user = rs.getString("avtc_user");
        String passwd = rs.getString("avtc_passwd");
        String sql = rs.getString("avtc_sql");

        builder.tableCalculator(table, name, type, url, user, passwd, sql);
        LOGGER.info(
            "View[{}]/Table[{}] build calculator: "
                + "name[{}], type[{}], url[{}], user[{}], passwd[{}], sql[{}]",
            builder.getTopic(),
            table,
            name,
            type,
            url,
            user,
            passwd,
            sql);
      }
    } finally {
      closeQuietly(conn, stmt, rs);
    }
  }

  private void closeQuietly(Connection conn, Statement stmt, ResultSet rs) {
    if (Objects.nonNull(rs)) {
      try {
        rs.close();
      } catch (SQLException e) {
        // do nothing
      }
    }

    if (Objects.nonNull(stmt)) {
      try {
        stmt.close();
      } catch (SQLException e) {
        // do nothing
      }
    }

    if (Objects.nonNull(conn)) {
      try {
        conn.close();
      } catch (SQLException e) {
        // do nothing
      }
    }
  }

  /**
   * Load view.
   *
   * @return views
   * @throws Exception If jdbc or sql error.
   */
  public List<DefaultView> load() throws Exception {
    List<DefaultView> views = new ArrayList<>();

    List<ViewBuilder> builders = fetchViewBuilders();
    for (ViewBuilder builder : builders) {
      LOGGER.info("View[{}] build start...", builder.getTopic());

      buildViewDimensions(builder);
      buildViewMetrics(builder);
      buildViewTables(builder);

      for (String table : builder.getTables()) {
        buildViewTableDimensions(builder, table);
        buildViewTableCalculators(builder, table);
      }

      views.add(builder.build());

      LOGGER.info("View[{}] build end", builder.getTopic());
    }

    return views;
  }
}
