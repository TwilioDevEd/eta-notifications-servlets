package com.twilio.etanotifications.lib;

import com.twilio.etanotifications.exceptions.UndefinedEnvironmentVariableException;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

/**
 * Class that holds methods to obtain configuration parameters from the environment.
 */
public class AppSetup {
  private static final Dotenv dotenv = Dotenv.load();

  public String getAccountSid() throws UndefinedEnvironmentVariableException {
    String sid = dotenv.get("TWILIO_ACCOUNT_SID");
    if (sid == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_ACCOUNT_SID is not defined");
    } else {
      return sid;
    }
  }

  public String getAuthToken() throws UndefinedEnvironmentVariableException {
    String token = dotenv.get("TWILIO_AUTH_TOKEN");
    if (token == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_AUTH_TOKEN is not set");
    } else {
      return token;
    }
  }

  public String getTwilioNumber() throws UndefinedEnvironmentVariableException {
    String phoneNumber = dotenv.get("TWILIO_NUMBER");
    if (phoneNumber == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_NUMBER is not set");
    } else {
      return phoneNumber;
    }
  }

  public String getDbURL() throws UndefinedEnvironmentVariableException {
    String url = dotenv.get("JDBC_URL");
    if (url == null) {
      throw new UndefinedEnvironmentVariableException("JDBC_URL is not defined");
    } else {
      return url;
    }
  }

  public String getDatabaseUsername() throws UndefinedEnvironmentVariableException {
    String username = dotenv.get("DB_USERNAME");
    if (username == null) {
      throw new UndefinedEnvironmentVariableException("DB_USERNAME is not defined");
    } else {
      return username;
    }
  }

  public String getDatabasePassword() {
    String password = dotenv.get("DB_PASSWORD");
    return password != null ? password : "";
  }
}
