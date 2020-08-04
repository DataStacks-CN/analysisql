package com.weibo.dip.analysis.view.db;

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
        ViewBuilder builder = new ViewBuilder();

        builder.topic(rs.getString("avi_topic"));
        builder.alias(rs.getString("avi_alias"));
        builder.desc(rs.getString("avi_desc"));

        builders.add(builder);
      }

      return builders;
    } finally {
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
        builder.dimension(
            rs.getString("avd_name"), rs.getString("avd_alias"), rs.getString("avd_desc"));
      }

    } finally {
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
        builder.metric(
            rs.getString("avm_name"), rs.getString("avm_alias"), rs.getString("avd_desc"));
      }

    } finally {
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
  }

  private void buildViewTables(ViewBuilder builder) throws Exception {}

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
      buildViewDimensions(builder);
      buildViewMetrics(builder);

      views.add(builder.build());
    }

    return views;
  }
}
