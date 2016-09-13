package com.twilio.etanotifications.lib;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public class MessageSender {
  private static MessageSender instance = new MessageSender();
  private TwilioRestClient client;
  private AppSetup appSetup;

  public MessageSender() {
    this.appSetup = new AppSetup();
    this.client = new TwilioRestClient.Builder(appSetup.getAccountSid(), appSetup.getAuthToken()).build();
  }

  public MessageSender(TwilioRestClient client, AppSetup appSetup) {
    this.client = client;
    this.appSetup = appSetup;
  }

  public static MessageSender getMessageSender() {
    return instance;
  }

  public int sendSMS(String toNumber, String messageBody, String callbackUrl) {
    try {
      new MessageCreator(
              new PhoneNumber(toNumber),
              new PhoneNumber(appSetup.getTwilioNumber()),
              messageBody
      ).setStatusCallback(callbackUrl).execute(client);
    } catch (Exception e) {
      e.printStackTrace();
      return 1;
    }
    return 0;
  }
}
