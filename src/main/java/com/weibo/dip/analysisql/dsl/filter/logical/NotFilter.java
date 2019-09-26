package com.weibo.dip.analysisql.dsl.filter.logical;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class NotFilter extends Filter {
  private Filter filter;

  public NotFilter() {
    super(Filter.NOT);
  }

  public NotFilter(Filter filter) {
    this();

    this.filter = filter;
  }

  public Filter getFilter() {
    return filter;
  }

  public void setFilter(Filter filter) {
    this.filter = filter;
  }
}
