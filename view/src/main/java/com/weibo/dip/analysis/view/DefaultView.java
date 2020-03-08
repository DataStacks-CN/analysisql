package com.weibo.dip.analysis.view;

import com.weibo.dip.analysisql.dsl.Parser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** DefaultView. */
public class DefaultView extends View {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultView.class);

  private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

  static {
    try {
      Class.forName(MYSQL_DRIVER);
    } catch (ClassNotFoundException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  private String url;
  private String username;
  private String password;

  private String table;

  public DefaultView(String topic) {
    super(topic);
  }

  /**
   * Initialize a instance with topic, url, username, password, db, table.
   *
   * @param topic topic
   * @param url mysql url
   * @param username mysql username
   * @param password mysql password
   * @param table mysql table
   */
  public DefaultView(String topic, String url, String username, String password, String table) {
    super(topic);

    this.url = url;
    this.username = username;
    this.password = password;
    this.table = table;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  @Override
  public List<String> getDimensionValues(String dimension) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      conn = DriverManager.getConnection(url, username, password);

      stmt = conn.createStatement();

      rs =
          stmt.executeQuery(
              String.format(
                  "SELECT MAX(dtime) FROM %s WHERE topic = '%s' AND dimension = '%s'",
                  table, topic, dimension));
      if (!rs.next()) {
        return null;
      }

      Timestamp timestamp = rs.getTimestamp(1);
      if (Objects.isNull(timestamp)) {
        return null;
      }

      rs =
          stmt.executeQuery(
              String.format(
                  "SELECT DISTINCT(dvalue) FROM %s WHERE "
                      + "topic = '%s' AND dimension = '%s' AND dtime = '%s'",
                  table, topic, dimension, Parser.DATETIME_FORMAT.format(timestamp.getTime())));
      if (!rs.next()) {
        return null;
      }

      List<String> values = new ArrayList<>();

      do {
        values.add(rs.getString(1));
      } while (rs.next());

      return values;
    } catch (Exception e) {
      LOGGER.error("get dimension values error: {}", ExceptionUtils.getStackTrace(e));

      return null;
    } finally {
      if (Objects.nonNull(rs)) {
        try {
          rs.close();
        } catch (SQLException e) {
          LOGGER.error("ResultSet close error: {}", ExceptionUtils.getStackTrace(e));
        }
      }

      if (Objects.nonNull(stmt)) {
        try {
          stmt.close();
        } catch (SQLException e) {
          LOGGER.error("Statement close error: {}", ExceptionUtils.getStackTrace(e));
        }
      }

      if (Objects.nonNull(conn)) {
        try {
          conn.close();
        } catch (SQLException e) {
          LOGGER.error("Connection close error: {}", ExceptionUtils.getStackTrace(e));
        }
      }
    }
  }
}
