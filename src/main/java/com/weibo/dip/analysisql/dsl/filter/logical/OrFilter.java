package com.weibo.dip.analysisql.dsl.filter.logical;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import java.util.List;

/** @author yurun */
public class OrFilter extends Filter {
  private List<Filter> filters;

  public OrFilter() {
    super(Filter.OR);
  }

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
}
