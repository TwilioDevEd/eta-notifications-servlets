package com.twilio.etanotifications.lib;

import com.twilio.etanotifications.exceptions.UndefinedEnvironmentVariableException;
import com.twilio.sdk.TwilioRestClient;

public class MessageSender {
  private TwilioRestClient client;

  public MessageSender() {
    AppSetup appSetup = new AppSetup();
    try {
      this.client = new TwilioRestClient(appSetup.getAccountSid(), appSetup.getAuthToken());
    } catch (UndefinedEnvironmentVariableException e) {
      e.printStackTrace();
    }
  }

  public MessageSender(TwilioRestClient client) {
    this.client = client;
  }
}
