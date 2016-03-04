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

public class NotificationStatusServletTest {
  private OrdersRepository ordersRepository = new OrdersRepository();
  @Mock
  private HttpServletResponse responseMock;

  @Mock
  private HttpServletRequest requestMock;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    ordersRepository.deleteAll();
  }

  @Test
  public void updateNotificationStatusTest()
      throws IOException, ServletException, TwilioRestException {
    NotificationStatusServlet servlet = new NotificationStatusServlet(ordersRepository);

    Order order = new Order("Vincent Vega", "+15551234567");
    ordersRepository.create(order);

    when(requestMock.getParameter("id")).thenReturn(String.valueOf(order.getId()));
    when(requestMock.getParameter("MessageStatus")).thenReturn("delivered");

    assertEquals("None", order.getNotificationStatus());
    servlet.doPost(requestMock, responseMock);

    order = ordersRepository.find(order.getId());

    assertEquals("Ready", order.getStatus());
    assertEquals("delivered", order.getNotificationStatus());
  }

}
