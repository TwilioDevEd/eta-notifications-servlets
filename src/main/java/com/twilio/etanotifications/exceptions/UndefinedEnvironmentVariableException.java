package com.twilio.etanotifications.exceptions;

/**
 * Exception raised when an environment variable is not defined.
 */
public class UndefinedEnvironmentVariableException extends RuntimeException {
  public UndefinedEnvironmentVariableException(String message) {
    super(message);
  }
}
