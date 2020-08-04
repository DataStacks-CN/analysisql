package com.weibo.dip.analysis.view.db;

/** ViewLoader. */
public class ViewLoader {
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
}
