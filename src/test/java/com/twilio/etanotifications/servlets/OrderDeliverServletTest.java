package com.twilio.etanotifications.servlets;

import com.twilio.etanotifications.lib.MessageSender;
import com.twilio.etanotifications.models.Order;
import com.twilio.etanotifications.repositories.OrdersRepository;
import com.twilio.sdk.TwilioRestException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderDeliverServletTest {
  private OrdersRepository ordersRepository = new OrdersRepository();
  @Mock
  private HttpServletResponse responseMock;

  @Mock
  private HttpServletRequest requestMock;

  @Mock
  private MessageSender messageSenderMock;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    ordersRepository.deleteAll();
  }

  @Test
  public void sendDeliverNotificationTest()
      throws IOException, ServletException, TwilioRestException {
    OrderDeliverServlet servlet = new OrderDeliverServlet(ordersRepository, messageSenderMock);

    Order order = new Order("Vincent Vega", "+15551234567");
    ordersRepository.create(order);

    when(requestMock.getParameter("id")).thenReturn(String.valueOf(order.getId()));
    when(requestMock.getRequestURL()).thenReturn(new StringBuffer("http://ngrok.io/some/path"));
    when(requestMock.getRequestURI()).thenReturn("/some/path");

    servlet.doPost(requestMock, responseMock);

    order = ordersRepository.find(order.getId());
    verify(messageSenderMock)
        .sendSMS(order.getCustomerPhoneNumber(), "Your laundry is arriving now.",
            String.format("http://ngrok.io/notification/status/update?id=%d", order.getId()));

    assertEquals("Delivered", order.getStatus());
    assertEquals("queued", order.getNotificationStatus());
  }

}
