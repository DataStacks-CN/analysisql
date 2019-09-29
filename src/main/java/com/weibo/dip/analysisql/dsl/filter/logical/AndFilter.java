package com.weibo.dip.analysisql.dsl.filter.logical;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import java.util.List;

/** AndFilter. */
public class AndFilter extends Filter {
  private List<Filter> filters;

  public AndFilter() {
    super(Filter.AND);
  }

  /**
   * Initializes a instance with filter list.
   *
   * @param filters filters
   */
  public AndFilter(List<Filter> filters) {
    this();

    this.filters = filters;
  }

  public List<Filter> getFilters() {
    return filters;
  }

  public void setFilters(List<Filter> filters) {
    this.filters = filters;
  }
}
