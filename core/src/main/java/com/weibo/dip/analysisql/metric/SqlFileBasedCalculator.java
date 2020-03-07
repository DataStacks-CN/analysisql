package com.weibo.dip.analysisql.metric;

import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import com.weibo.dip.analysisql.response.Row;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** SqlFileBasedCalculator. */
public abstract class SqlFileBasedCalculator implements MetricCalculator {
  private static final Logger LOGGER = LoggerFactory.getLogger(SqlFileBasedCalculator.class);

  protected SqlTemplate sqlTemplate;

  protected String sqlFile;

  public SqlFileBasedCalculator(SqlTemplate sqlTemplate, String sqlFile) {
    this.sqlTemplate = sqlTemplate;
    this.sqlFile = sqlFile;
  }

  public SqlTemplate getSqlTemplate() {
    return sqlTemplate;
  }

  public void setSqlTemplate(SqlTemplate sqlTemplate) {
    this.sqlTemplate = sqlTemplate;
  }

  public String getSqlFile() {
    return sqlFile;
  }

  public void setSqlFile(String sqlFile) {
    this.sqlFile = sqlFile;
  }

  public abstract List<Row> query(String sql) throws Exception;

  @Override
  public List<Row> calculate(QueryRequest request) throws Exception {
    String sql = sqlTemplate.render(sqlFile, request);
    LOGGER.info("sessionId: {}, sql: {}", request.getSessionId(), sql);

    return query(sql);
  }
}
