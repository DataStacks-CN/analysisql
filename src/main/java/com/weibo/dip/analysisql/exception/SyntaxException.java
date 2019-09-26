package com.weibo.dip.analysisql.exception;

/** @author yurun */
public class SyntaxException extends RuntimeException {
  public SyntaxException() {
    super();
  }

  public SyntaxException(String message) {
    super(message);
  }

  public SyntaxException(Throwable cause) {
    super(cause);
  }

  public SyntaxException(String message, Throwable cause) {
    super(message, cause);
  }
}
