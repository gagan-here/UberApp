package com.uber.uberapp.exceptions;

public class RunTimeConflictException extends RuntimeException {
  public RunTimeConflictException() {}

  public RunTimeConflictException(String message) {
    super(message);
  }
}
