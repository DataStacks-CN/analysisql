package com.weibo.dip.analysis.view;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** GetDimensionValuesTester. */
public class GetDimensionValuesTester {
  private static final Logger LOGGER = LoggerFactory.getLogger(GetDimensionValuesTester.class);

  /**
   * Main.
   *
   * @param args topic, url, username, passowrd, table, dimension
   */
  public static void main(String[] args) {
    if (ArrayUtils.isEmpty(args) || args.length != 7) {
      System.out.println("args: topic, url, username, passowrd, table, dimension");
      return;
    }

    DefaultView view = new DefaultView(args[0], args[1], args[2], args[3], args[4]);

    LOGGER.info("values: {}", view.getDimensionValues(args[5]));
  }
}
