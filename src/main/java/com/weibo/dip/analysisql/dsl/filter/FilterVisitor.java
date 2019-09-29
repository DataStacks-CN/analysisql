package com.weibo.dip.analysisql.dsl.filter;

import com.weibo.dip.analysisql.dsl.filter.logical.AndFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.NotFilter;
import com.weibo.dip.analysisql.dsl.filter.logical.OrFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.DoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.EqDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.EqLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.EqStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GeDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GeLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GeStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GtDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GtLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.GtStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.InDoubleArrayRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.InLongArrayRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.InStringArrayRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LeDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LeLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LeStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LtDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LtLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.LtStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.NeDoubleRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.NeLongRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.NeStringRelationalFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.RegexFilter;
import com.weibo.dip.analysisql.dsl.filter.relational.StringRelationalFilter;

/** FilterVisitor. */
public abstract class FilterVisitor<T> {
  abstract T visitAndFilter(AndFilter filter);

  abstract T visitOrFilter(OrFilter filter);

  abstract T visitNotFilter(NotFilter filter);

  abstract T visitEqDoubleRelationalFilter(EqDoubleRelationalFilter filter);

  abstract T visitEqLongRelationalFilter(EqLongRelationalFilter filter);

  abstract T visitEqStringRelationalFilter(EqStringRelationalFilter filter);

  abstract T visitNeDoubleRelationalFilter(NeDoubleRelationalFilter filter);

  abstract T visitNeLongRelationalFilter(NeLongRelationalFilter filter);

  abstract T visitNeStringRelationalFilter(NeStringRelationalFilter filter);

  abstract T visitGtDoubleRelationalFilter(GtDoubleRelationalFilter filter);

  abstract T visitGtLongRelationalFilter(GtLongRelationalFilter filter);

  abstract T visitGtStringRelationalFilter(GtStringRelationalFilter filter);

  abstract T visitLtDoubleRelationalFilter(LtDoubleRelationalFilter filter);

  abstract T visitLtLongRelationalFilter(LtLongRelationalFilter filter);

  abstract T visitLtStringRelationalFilter(LtStringRelationalFilter filter);

  abstract T visitGeDoubleRelationalFilter(GeDoubleRelationalFilter filter);

  abstract T visitGeLongRelationalFilter(GeLongRelationalFilter filter);

  abstract T visitGeStringRelationalFilter(GeStringRelationalFilter filter);

  abstract T visitLeDoubleRelationalFilter(LeDoubleRelationalFilter filter);

  abstract T visitLeLongRelationalFilter(LeLongRelationalFilter filter);

  abstract T visitLeStringRelationalFilter(LeStringRelationalFilter filter);

  abstract T visitInDoubleArrayRelationalFilter(InDoubleArrayRelationalFilter filter);

  abstract T visitInLongArrayRelationalFilter(InLongArrayRelationalFilter filter);

  abstract T visitInStringArrayRelationalFilter(InStringArrayRelationalFilter filter);

  abstract T visitRegexFilter(RegexFilter filter);

  /**
   * Visit double relational filter.
   *
   * @param filter filter
   * @return result
   */
  public T visitDoubleRelationalFilter(DoubleRelationalFilter filter) {
    if (filter instanceof EqDoubleRelationalFilter) {
      return visitEqDoubleRelationalFilter((EqDoubleRelationalFilter) filter);
    } else if (filter instanceof NeDoubleRelationalFilter) {
      return visitNeDoubleRelationalFilter((NeDoubleRelationalFilter) filter);
    } else if (filter instanceof GtDoubleRelationalFilter) {
      return visitGtDoubleRelationalFilter((GtDoubleRelationalFilter) filter);
    } else if (filter instanceof LtDoubleRelationalFilter) {
      return visitLtDoubleRelationalFilter((LtDoubleRelationalFilter) filter);
    } else if (filter instanceof GeDoubleRelationalFilter) {
      return visitGeDoubleRelationalFilter((GeDoubleRelationalFilter) filter);
    } else if (filter instanceof LeDoubleRelationalFilter) {
      return visitLeDoubleRelationalFilter((LeDoubleRelationalFilter) filter);
    } else {
      return null;
    }
  }

  /**
   * Visit long relational filter.
   *
   * @param filter filter
   * @return result
   */
  public T visitLongRelationalFilter(LongRelationalFilter filter) {
    if (filter instanceof EqLongRelationalFilter) {
      return visitEqLongRelationalFilter((EqLongRelationalFilter) filter);
    } else if (filter instanceof NeLongRelationalFilter) {
      return visitNeLongRelationalFilter((NeLongRelationalFilter) filter);
    } else if (filter instanceof GtLongRelationalFilter) {
      return visitGtLongRelationalFilter((GtLongRelationalFilter) filter);
    } else if (filter instanceof LtLongRelationalFilter) {
      return visitLtLongRelationalFilter((LtLongRelationalFilter) filter);
    } else if (filter instanceof GeLongRelationalFilter) {
      return visitGeLongRelationalFilter((GeLongRelationalFilter) filter);
    } else if (filter instanceof LeLongRelationalFilter) {
      return visitLeLongRelationalFilter((LeLongRelationalFilter) filter);
    } else {
      return null;
    }
  }

  /**
   * Visit string relational filter.
   *
   * @param filter filter
   * @return result
   */
  public T visitStringRelationalFilter(StringRelationalFilter filter) {
    if (filter instanceof EqStringRelationalFilter) {
      return visitEqStringRelationalFilter((EqStringRelationalFilter) filter);
    } else if (filter instanceof NeStringRelationalFilter) {
      return visitNeStringRelationalFilter((NeStringRelationalFilter) filter);
    } else if (filter instanceof GtStringRelationalFilter) {
      return visitGtStringRelationalFilter((GtStringRelationalFilter) filter);
    } else if (filter instanceof LtStringRelationalFilter) {
      return visitLtStringRelationalFilter((LtStringRelationalFilter) filter);
    } else if (filter instanceof GeStringRelationalFilter) {
      return visitGeStringRelationalFilter((GeStringRelationalFilter) filter);
    } else if (filter instanceof LeStringRelationalFilter) {
      return visitLeStringRelationalFilter((LeStringRelationalFilter) filter);
    } else {
      return null;
    }
  }

  /**
   * Visit filter.
   *
   * @param filter filter
   * @return result
   */
  public T visit(Filter filter) {
    if (filter instanceof AndFilter) {
      return visitAndFilter((AndFilter) filter);
    } else if (filter instanceof OrFilter) {
      return visitOrFilter((OrFilter) filter);
    } else if (filter instanceof NotFilter) {
      return visitNotFilter((NotFilter) filter);
    } else if (filter instanceof StringRelationalFilter) {
      return visitStringRelationalFilter((StringRelationalFilter) filter);
    } else if (filter instanceof DoubleRelationalFilter) {
      return visitDoubleRelationalFilter((DoubleRelationalFilter) filter);
    } else if (filter instanceof LongRelationalFilter) {
      return visitLongRelationalFilter((LongRelationalFilter) filter);
    } else if (filter instanceof InStringArrayRelationalFilter) {
      return visitInStringArrayRelationalFilter((InStringArrayRelationalFilter) filter);
    } else if (filter instanceof InDoubleArrayRelationalFilter) {
      return visitInDoubleArrayRelationalFilter((InDoubleArrayRelationalFilter) filter);
    } else if (filter instanceof InLongArrayRelationalFilter) {
      return visitInLongArrayRelationalFilter((InLongArrayRelationalFilter) filter);
    } else if (filter instanceof RegexFilter) {
      return visitRegexFilter((RegexFilter) filter);
    } else {
      return null;
    }
  }
}
