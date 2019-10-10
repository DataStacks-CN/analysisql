package com.weibo.dip.analysisql.dsl.filter;

import com.weibo.dip.analysisql.dsl.filter.in.DoubleInFilter;
import com.weibo.dip.analysisql.dsl.filter.in.InFilter;
import com.weibo.dip.analysisql.dsl.filter.in.LongInFilter;
import com.weibo.dip.analysisql.dsl.filter.in.StringInFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.AndFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.NotFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.OrFilter;
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
  protected abstract T visitAnd(AndFilter filter);

  protected abstract T visitOr(OrFilter filter);

  protected abstract T visitNot(NotFilter filter);

  protected abstract T visitStringEq(StringEqFilter filter);

  protected abstract T visitLongEq(LongEqFilter filter);

  protected abstract T visitDoubleEq(DoubleEqFilter filter);

  /**
   * Visit eq filter.
   *
   * @param filter eq filter
   * @return return
   */
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

  protected abstract T visitStringNe(StringNeFilter filter);

  protected abstract T visitLongNe(LongNeFilter filter);

  protected abstract T visitDoubleNe(DoubleNeFilter filter);

  /**
   * Visit ne filter.
   *
   * @param filter ne filter
   * @return result
   */
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

  protected abstract T visitStringGt(StringGtFilter filter);

  protected abstract T visitLongGt(LongGtFilter filter);

  protected abstract T visitDoubleGt(DoubleGtFilter filter);

  /**
   * Visit gt filter.
   *
   * @param filter gt filter
   * @return result
   */
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

  protected abstract T visitStringLt(StringLtFilter filter);

  protected abstract T visitLongLt(LongLtFilter filter);

  protected abstract T visitDoubleLt(DoubleLtFilter filter);

  /**
   * Visit lt filter.
   *
   * @param filter lt filter
   * @return result
   */
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

  protected abstract T visitStringGe(StringGeFilter filter);

  protected abstract T visitLongGe(LongGeFilter filter);

  protected abstract T visitDoubleGe(DoubleGeFilter filter);

  /**
   * Visit ge filter.
   *
   * @param filter ge filter
   * @return result
   */
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

  protected abstract T visitStringLe(StringLeFilter filter);

  protected abstract T visitLongLe(LongLeFilter filter);

  protected abstract T visitDoubleLe(DoubleLeFilter filter);

  /**
   * Visit le filter.
   *
   * @param filter le filter
   * @return result
   */
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

  protected abstract T visitStringIn(StringInFilter filter);

  protected abstract T visitLongIn(LongInFilter filter);

  protected abstract T visitDoubleIn(DoubleInFilter filter);

  /**
   * Visit in filter.
   *
   * @param filter in filter
   * @return result
   */
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
