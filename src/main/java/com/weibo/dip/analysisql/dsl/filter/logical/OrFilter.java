package com.weibo.dip.analysisql.dsl.filter.logical;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** OrFilter. */
public class OrFilter extends Filter {
  private List<Filter> filters;

  public OrFilter() {
    super(Filter.OR);
  }

  /**
   * Initializes a instance with filter list.
   *
   * @param filters filters
   */
  public OrFilter(List<Filter> filters) {
    this();

    this.filters = filters;
  }

  public List<Filter> getFilters() {
    return filters;
  }

  public void setFilters(List<Filter> filters) {
    this.filters = filters;
  }

  public void add(Filter filter) {
    if (Objects.isNull(filters)) {
      filters = new ArrayList<>();
    }

    filters.add(filter);
  }
}
