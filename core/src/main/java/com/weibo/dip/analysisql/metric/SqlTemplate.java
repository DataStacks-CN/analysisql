package com.weibo.dip.analysisql.metric;

import com.weibo.dip.analysisql.dsl.request.QueryRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/** SqlTemplate. */
public abstract class SqlTemplate {
  public static final String TIME_BUCKET = "timeBucket";

  protected String load(String resource) throws IOException {
    InputStream in = null;

    try {
      in = SqlTemplate.class.getClassLoader().getResourceAsStream(resource);
      assert Objects.nonNull(in);

      List<String> lines =
          IOUtils.readLines(in, StandardCharsets.UTF_8).stream()
              .map(String::trim)
              .collect(Collectors.toList());
      return StringUtils.join(lines, StringUtils.SPACE);
    } finally {
      if (Objects.nonNull(in)) {
        in.close();
      }
    }
  }

  public abstract String render(String resource, QueryRequest request) throws Exception;
}
