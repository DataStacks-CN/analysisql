package com.weibo.dip.analysis.view;

import com.weibo.dip.analysisql.dsl.filter.FilterVisitor;
import com.weibo.dip.analysisql.dsl.filter.RegexFilter;
import com.weibo.dip.analysisql.dsl.filter.in.DoubleInFilter;
import com.weibo.dip.analysisql.dsl.filter.in.LongInFilter;
import com.weibo.dip.analysisql.dsl.filter.in.StringInFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.AndFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.NotFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.OrFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.eq.DoubleEqFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.eq.LongEqFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.eq.StringEqFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ge.DoubleGeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ge.LongGeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ge.StringGeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.gt.DoubleGtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.gt.LongGtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.gt.StringGtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.le.DoubleLeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.le.LongLeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.le.StringLeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.lt.DoubleLtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.lt.LongLtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.lt.StringLtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ne.DoubleNeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ne.LongNeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ne.StringNeFilter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/** FilterDimensionVisitor. */
public class FilterDimensionVisitor extends FilterVisitor<Set<String>> {

  @Override
  protected Set<String> visitAnd(AndFilter filter) {
    Set<String> dimensions = new HashSet<>();

    filter.getFilters().forEach(subFilter -> dimensions.addAll(visit(subFilter)));

    return dimensions;
  }

  @Override
  protected Set<String> visitOr(OrFilter filter) {
    Set<String> dimensions = new HashSet<>();

    filter.getFilters().forEach(subFilter -> dimensions.addAll(visit(subFilter)));

    return dimensions;
  }

  @Override
  protected Set<String> visitNot(NotFilter filter) {
    return visit(filter.getFilter());
  }

  @Override
  protected Set<String> visitStringEq(StringEqFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitLongEq(LongEqFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitDoubleEq(DoubleEqFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitStringNe(StringNeFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitLongNe(LongNeFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitDoubleNe(DoubleNeFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitStringGt(StringGtFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitLongGt(LongGtFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitDoubleGt(DoubleGtFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitStringLt(StringLtFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitLongLt(LongLtFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitDoubleLt(DoubleLtFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitStringGe(StringGeFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitLongGe(LongGeFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitDoubleGe(DoubleGeFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitStringLe(StringLeFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitLongLe(LongLeFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitDoubleLe(DoubleLeFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitStringIn(StringInFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitLongIn(LongInFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  protected Set<String> visitDoubleIn(DoubleInFilter filter) {
    return Collections.singleton(filter.getName());
  }

  @Override
  public Set<String> visitRegex(RegexFilter filter) {
    return Collections.singleton(filter.getName());
  }
}
