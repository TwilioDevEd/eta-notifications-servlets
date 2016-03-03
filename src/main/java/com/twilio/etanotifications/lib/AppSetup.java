package com.twilio.etanotifications.lib;

import com.twilio.etanotifications.exceptions.UndefinedEnvironmentVariableException;

import java.util.Map;

/**
 * Class that holds methods to obtain configuration parameters from the environment.
 */
public class AppSetup {
  private Map<String, String> env;

  public AppSetup() {
    this.env = System.getenv();
  }

  public String getAccountSid() throws UndefinedEnvironmentVariableException {
    String sid = env.get("TWILIO_ACCOUNT_SID");
    if (sid == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_ACCOUNT_SID is not defined");
    } else {
      return sid;
    }
  }

  public String getAuthToken() throws UndefinedEnvironmentVariableException {
    String token = env.get("TWILIO_AUTH_TOKEN");
    if (token == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_AUTH_TOKEN is not set");
    } else {
      return token;
    }
  }

  public String getTwilioNumber() throws UndefinedEnvironmentVariableException {
    String phoneNumber = env.get("TWILIO_NUMBER");
    if (phoneNumber == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_NUMBER is not set");
    } else {
      return phoneNumber;
    }
  }

  public String getDbURL() throws UndefinedEnvironmentVariableException {
    String url = env.get("JDBC_URL");
    if (url == null) {
      throw new UndefinedEnvironmentVariableException("JDBC_URL is not defined");
    } else {
      return url;
    }
  }

  public String getDatabaseUsername() throws UndefinedEnvironmentVariableException {
    String username = env.get("DB_USER");
    if (username == null) {
      throw new UndefinedEnvironmentVariableException("DB_USER is not defined");
    } else {
      return username;
    }
  }

  public String getDatabasePassword() {
    String password = env.get("DB_PASSWORD");
    return password != null ? password : "";
  }
}
