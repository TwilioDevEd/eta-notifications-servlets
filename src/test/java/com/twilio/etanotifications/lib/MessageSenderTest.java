package com.twilio.etanotifications.lib;

import com.twilio.etanotifications.exceptions.UndefinedEnvironmentVariableException;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessageSenderTest {
  @Mock
  private TwilioRestClient client;

  @Mock
  private AppSetup appSetup;

  @Mock
  private Account account;

  @Mock
  private MessageFactory messageFactory;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSendSMS() throws UndefinedEnvironmentVariableException, TwilioRestException {
    MessageSender sender = new MessageSender(client, appSetup);
    String toNumber = "+15551234567";
    String messageBody = "My own custom message";
    String callbackUrl = "http://www.twilio.com";
    String twilioNumber = "+10987654222";

    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("To", toNumber));
    params.add(new BasicNameValuePair("From", twilioNumber));
    params.add(new BasicNameValuePair("Body", messageBody));
    params.add(new BasicNameValuePair("StatusCallback", callbackUrl));

    when(appSetup.getTwilioNumber()).thenReturn(twilioNumber);
    when(client.getAccount()).thenReturn(account);
    when(account.getMessageFactory()).thenReturn(messageFactory);

    int success = sender.sendSMS(toNumber, messageBody, callbackUrl);

    verify(messageFactory).create(params);
    assertEquals(0, success);
  }

  @Test(expected = TwilioRestException.class)
  public void testReturnsErrorCode()
      throws UndefinedEnvironmentVariableException, TwilioRestException {
    MessageSender sender = new MessageSender(client, appSetup);

    when(appSetup.getTwilioNumber()).thenReturn("some number");
    when(client.getAccount()).thenReturn(account);
    when(account.getMessageFactory()).thenReturn(messageFactory);
    when(messageFactory.create(anyList()))
        .thenThrow(new TwilioRestException("something is wrong!", 123));

    sender.sendSMS("to number", "message body", "some url");
  }
}
