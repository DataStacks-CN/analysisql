package com.weibo.dip.analysisql.dsl.filter;

import com.weibo.dip.analysisql.dsl.filter.logical.AndFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.NotFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.OrFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.RegexFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.RelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.eq.DoubleEqFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.eq.EqFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.eq.LongEqFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.eq.StringEqFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ge.DoubleGeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ge.GeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ge.LongGeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ge.StringGeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.gt.DoubleGtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.gt.GtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.gt.LongGtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.gt.StringGtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.in.DoubleInFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.in.InFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.in.LongInFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.in.StringInFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.le.DoubleLeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.le.LeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.le.LongLeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.le.StringLeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.lt.DoubleLtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.lt.LongLtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.lt.LtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.lt.StringLtFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ne.DoubleNeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ne.LongNeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ne.NeFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.ne.StringNeFilter;
import java.util.Objects;

/** FilterVisitor. */
public abstract class FilterVisitor<T> {
  abstract T visitAnd(AndFilter filter);

  abstract T visitOr(OrFilter filter);

  abstract T visitNot(NotFilter filter);

  abstract T visitStringEq(StringEqFilter filter);

  abstract T visitLongEq(LongEqFilter filter);

  abstract T visitDoubleEq(DoubleEqFilter filter);

  public T visitEq(EqFilter filter) {
    String type = filter.getType();

    switch (type) {
      case RelationalFilter.STRING:
        return visitStringEq((StringEqFilter) filter);
      case RelationalFilter.LONG:
        return visitLongEq((LongEqFilter) filter);
      case RelationalFilter.DOUBLE:
        return visitDoubleEq((DoubleEqFilter) filter);
      default:
        return null;
    }
  }

  abstract T visitStringNe(StringNeFilter filter);

  abstract T visitLongNe(LongNeFilter filter);

  abstract T visitDoubleNe(DoubleNeFilter filter);

  public T visitNe(NeFilter filter) {
    String type = filter.getType();

    switch (type) {
      case RelationalFilter.STRING:
        return visitStringNe((StringNeFilter) filter);
      case RelationalFilter.LONG:
        return visitLongNe((LongNeFilter) filter);
      case RelationalFilter.DOUBLE:
        return visitDoubleNe((DoubleNeFilter) filter);
      default:
        return null;
    }
  }

  abstract T visitStringGt(StringGtFilter filter);

  abstract T visitLongGt(LongGtFilter filter);

  abstract T visitDoubleGt(DoubleGtFilter filter);

  public T visitGt(GtFilter filter) {
    String type = filter.getType();

    switch (type) {
      case RelationalFilter.STRING:
        return visitStringGt((StringGtFilter) filter);
      case RelationalFilter.LONG:
        return visitLongGt((LongGtFilter) filter);
      case RelationalFilter.DOUBLE:
        return visitDoubleGt((DoubleGtFilter) filter);
      default:
        return null;
    }
  }

  abstract T visitStringLt(StringLtFilter filter);

  abstract T visitLongLt(LongLtFilter filter);

  abstract T visitDoubleLt(DoubleLtFilter filter);

  public T visitLt(LtFilter filter) {
    String type = filter.getType();

    switch (type) {
      case RelationalFilter.STRING:
        return visitStringLt((StringLtFilter) filter);
      case RelationalFilter.LONG:
        return visitLongLt((LongLtFilter) filter);
      case RelationalFilter.DOUBLE:
        return visitDoubleLt((DoubleLtFilter) filter);
      default:
        return null;
    }
  }

  abstract T visitStringGe(StringGeFilter filter);

  abstract T visitLongGe(LongGeFilter filter);

  abstract T visitDoubleGe(DoubleGeFilter filter);

  public T visitGe(GeFilter filter) {
    String type = filter.getType();

    switch (type) {
      case RelationalFilter.STRING:
        return visitStringGe((StringGeFilter) filter);
      case RelationalFilter.LONG:
        return visitLongGe((LongGeFilter) filter);
      case RelationalFilter.DOUBLE:
        return visitDoubleGe((DoubleGeFilter) filter);
      default:
        return null;
    }
  }

  abstract T visitStringLe(StringLeFilter filter);

  abstract T visitLongLe(LongLeFilter filter);

  abstract T visitDoubleLe(DoubleLeFilter filter);

  public T visitLe(LeFilter filter) {
    String type = filter.getType();

    switch (type) {
      case RelationalFilter.STRING:
        return visitStringLe((StringLeFilter) filter);
      case RelationalFilter.LONG:
        return visitLongLe((LongLeFilter) filter);
      case RelationalFilter.DOUBLE:
        return visitDoubleLe((DoubleLeFilter) filter);
      default:
        return null;
    }
  }

  abstract T visitStringIn(StringInFilter filter);

  abstract T visitLongIn(LongInFilter filter);

  abstract T visitDoubleIn(DoubleInFilter filter);

  public T visitIn(InFilter filter) {
    String type = filter.getType();

    switch (type) {
      case RelationalFilter.STRING:
        return visitStringIn((StringInFilter) filter);
      case RelationalFilter.LONG:
        return visitLongIn((LongInFilter) filter);
      case RelationalFilter.DOUBLE:
        return visitDoubleIn((DoubleInFilter) filter);
      default:
        return null;
    }
  }

  public abstract T visitRegex(RegexFilter filter);

  /**
   * Visit filter.
   *
   * @param filter filter
   * @return result
   */
  public T visit(Filter filter) {
    if (Objects.isNull(filter)) {
      return null;
    }

    String operator = filter.getOperator();

    switch (operator) {
      case Filter.AND:
        return visitAnd((AndFilter) filter);
      case Filter.OR:
        return visitOr((OrFilter) filter);
      case Filter.NOT:
        return visitNot((NotFilter) filter);
      case Filter.EQ:
        return visitEq((EqFilter) filter);
      case Filter.NE:
        return visitNe((NeFilter) filter);
      case Filter.GT:
        return visitGt((GtFilter) filter);
      case Filter.LT:
        return visitLt((LtFilter) filter);
      case Filter.GE:
        return visitGe((GeFilter) filter);
      case Filter.LE:
        return visitLe((LeFilter) filter);
      case Filter.IN:
        return visitIn((InFilter) filter);
      case Filter.REGEX:
        return visitRegex((RegexFilter) filter);
      default:
        return null;
    }
  }
}
