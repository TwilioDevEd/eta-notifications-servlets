package com.twilio.etanotifications.lib;

import com.twilio.etanotifications.exceptions.UndefinedEnvironmentVariableException;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MessageSender {
  private static MessageSender instance = new MessageSender();
  private TwilioRestClient client;
  private AppSetup appSetup;

  public static MessageSender getMessageSender() {
    return instance;
  }

  public MessageSender() {
    this.appSetup = new AppSetup();
    try {
      this.client = new TwilioRestClient(appSetup.getAccountSid(), appSetup.getAuthToken());
    } catch (UndefinedEnvironmentVariableException e) {
      e.printStackTrace();
    }
  }

  public MessageSender(TwilioRestClient client, AppSetup appSetup) {
    this.client = client;
    this.appSetup = appSetup;
  }

  public int sendSMS(String toNumber, String messageBody, String callbackUrl) {
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("To", toNumber));
    try {
      params.add(new BasicNameValuePair("From", appSetup.getTwilioNumber()));
    } catch (UndefinedEnvironmentVariableException e) {
      e.printStackTrace();
      return 1;
    }
    params.add(new BasicNameValuePair("Body", messageBody));
    params.add(new BasicNameValuePair("StatusCallback", callbackUrl));

    MessageFactory messageFactory = client.getAccount().getMessageFactory();
    try {
      messageFactory.create(params);
    } catch (TwilioRestException e) {
      e.printStackTrace();
      return e.getErrorCode();
    }

    return 0;
  }
}
