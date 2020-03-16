package com.weibo.dip.analysisql.metric;

import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.response.Row;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** SqlFileBasedCalculator. */
public abstract class SqlFileBasedCalculator implements MetricCalculator {
  private static final Logger LOGGER = LoggerFactory.getLogger(SqlFileBasedCalculator.class);

  protected SqlTemplate sqlTemplate;

  protected String sql;

  /**
   * Initialize a instance with sql template, sql file.
   *
   * @param sqlTemplate SqlTemplate
   * @param sqlFile Sql file path
   */
  public SqlFileBasedCalculator(SqlTemplate sqlTemplate, String sqlFile) {
    this.sqlTemplate = sqlTemplate;

    if (Objects.nonNull(sqlFile)) {
      this.sql = load(sqlFile);
    } else {
      this.sql = null;
    }
  }

  public SqlTemplate getSqlTemplate() {
    return sqlTemplate;
  }

  public String getSql() {
    return sql;
  }

  public void setSqlTemplate(SqlTemplate sqlTemplate) {
    this.sqlTemplate = sqlTemplate;
  }

  protected String load(String resource) {
    InputStream in = null;

    try {
      in = SqlTemplate.class.getClassLoader().getResourceAsStream(resource);
      assert Objects.nonNull(in);

      List<String> lines =
          IOUtils.readLines(in, StandardCharsets.UTF_8).stream()
              .map(String::trim)
              .collect(Collectors.toList());

      return StringUtils.join(lines, StringUtils.SPACE);
    } catch (IOException e) {
      LOGGER.error("load resource error: {}", ExceptionUtils.getStackTrace(e));

      return null;
    } finally {
      if (Objects.nonNull(in)) {
        try {
          in.close();
        } catch (IOException e) {
          // do nothing
        }
      }
    }
  }

  public abstract List<Row> query(String sql) throws Exception;

  @Override
  public List<Row> calculate(QueryRequest request) throws Exception {
    String sql = sqlTemplate.render(this.sql, request);
    LOGGER.info("sessionId: {}, sql: {}", request.getSessionId(), sql);

    return query(sql);
  }
}
