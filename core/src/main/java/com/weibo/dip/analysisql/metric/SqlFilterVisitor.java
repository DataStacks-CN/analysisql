package com.weibo.dip.analysisql.metric;

import com.weibo.dip.analysisql.dsl.filter.Filter;
import com.weibo.dip.analysisql.dsl.filter.FilterVisitor;
import com.weibo.dip.analysisql.dsl.filter.RegexFilter;
import com.weibo.dip.analysisql.dsl.filter.in.DoubleInFilter;
import com.weibo.dip.analysisql.dsl.filter.in.InFilter;
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
import java.util.List;
import java.util.StringJoiner;

/** PinotFilterVisitor. */
public class SqlFilterVisitor extends FilterVisitor<String> {
  @Override
  protected String visitAnd(AndFilter filter) {
    List<Filter> childFilters = filter.getFilters();

    StringJoiner block = new StringJoiner(" AND ");

    for (Filter childFilter : childFilters) {
      block.add(visit(childFilter));
    }

    return block.toString();
  }

  @Override
  protected String visitOr(OrFilter filter) {
    List<Filter> childFilters = filter.getFilters();

    StringJoiner block = new StringJoiner(" OR ");

    for (Filter childFilter : childFilters) {
      block.add(visit(childFilter));
    }

    return block.toString();
  }

  @Override
  protected String visitNot(NotFilter filter) {
    Filter childFilter = filter.getFilter();
    if (!(childFilter instanceof InFilter)) {
      throw new UnsupportedOperationException("Operator 'not' only support with 'in'");
    }

    return visit(childFilter).replace("IN", "NOT IN");
  }

  @Override
  protected String visitStringEq(StringEqFilter filter) {
    StringJoiner block = new StringJoiner(" = ");

    block.add(filter.getName());
    block.add("'" + filter.getValue() + "'");

    return block.toString();
  }

  @Override
  protected String visitLongEq(LongEqFilter filter) {
    StringJoiner block = new StringJoiner(" = ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitDoubleEq(DoubleEqFilter filter) {
    StringJoiner block = new StringJoiner(" = ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitStringNe(StringNeFilter filter) {
    StringJoiner block = new StringJoiner(" != ");

    block.add(filter.getName());
    block.add("'" + filter.getValue() + "'");

    return block.toString();
  }

  @Override
  protected String visitLongNe(LongNeFilter filter) {
    StringJoiner block = new StringJoiner(" != ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitDoubleNe(DoubleNeFilter filter) {
    StringJoiner block = new StringJoiner(" != ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitStringGt(StringGtFilter filter) {
    StringJoiner block = new StringJoiner(" > ");

    block.add(filter.getName());
    block.add("'" + filter.getValue() + "'");

    return block.toString();
  }

  @Override
  protected String visitLongGt(LongGtFilter filter) {
    StringJoiner block = new StringJoiner(" > ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitDoubleGt(DoubleGtFilter filter) {
    StringJoiner block = new StringJoiner(" > ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitStringLt(StringLtFilter filter) {
    StringJoiner block = new StringJoiner(" < ");

    block.add(filter.getName());
    block.add("'" + filter.getValue() + "'");

    return block.toString();
  }

  @Override
  protected String visitLongLt(LongLtFilter filter) {
    StringJoiner block = new StringJoiner(" < ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitDoubleLt(DoubleLtFilter filter) {
    StringJoiner block = new StringJoiner(" < ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitStringGe(StringGeFilter filter) {
    StringJoiner block = new StringJoiner(" >= ");

    block.add(filter.getName());
    block.add("'" + filter.getValue() + "'");

    return block.toString();
  }

  @Override
  protected String visitLongGe(LongGeFilter filter) {
    StringJoiner block = new StringJoiner(" >= ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitDoubleGe(DoubleGeFilter filter) {
    StringJoiner block = new StringJoiner(" >= ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitStringLe(StringLeFilter filter) {
    StringJoiner block = new StringJoiner(" <= ");

    block.add(filter.getName());
    block.add("'" + filter.getValue() + "'");

    return block.toString();
  }

  @Override
  protected String visitLongLe(LongLeFilter filter) {
    StringJoiner block = new StringJoiner(" <= ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitDoubleLe(DoubleLeFilter filter) {
    StringJoiner block = new StringJoiner(" <= ");

    block.add(filter.getName());
    block.add(String.valueOf(filter.getValue()));

    return block.toString();
  }

  @Override
  protected String visitStringIn(StringInFilter filter) {
    StringJoiner block = new StringJoiner(" ");

    block.add(filter.getName());
    block.add("IN");

    StringJoiner array = new StringJoiner(" , ", "(", ")");

    String[] values = filter.getValues();

    for (String value : values) {
      array.add("'" + value + "'");
    }

    block.add(array.toString());

    return block.toString();
  }

  @Override
  protected String visitLongIn(LongInFilter filter) {
    StringJoiner block = new StringJoiner(" ");

    block.add(filter.getName());
    block.add("IN");

    StringJoiner array = new StringJoiner(" , ", "(", ")");

    Long[] values = filter.getValues();

    for (Long value : values) {
      array.add(String.valueOf(value));
    }

    block.add(array.toString());

    return block.toString();
  }

  @Override
  protected String visitDoubleIn(DoubleInFilter filter) {
    StringJoiner block = new StringJoiner(" ");

    block.add(filter.getName());
    block.add("IN");

    StringJoiner array = new StringJoiner(" , ", "(", ")");

    Double[] values = filter.getValues();

    for (Double value : values) {
      array.add(String.valueOf(value));
    }

    block.add(array.toString());

    return block.toString();
  }

  @Override
  public String visitRegex(RegexFilter filter) {
    return filter.getName() + " REGEXP " + "'" + filter.getPattern() + "'";
  }
}
