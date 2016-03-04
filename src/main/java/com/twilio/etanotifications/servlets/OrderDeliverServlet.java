package com.twilio.etanotifications.servlets;

import com.twilio.etanotifications.lib.MessageSender;
import com.twilio.etanotifications.models.Order;
import com.twilio.etanotifications.repositories.OrdersRepository;
import com.twilio.sdk.TwilioRestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderDeliverServlet extends HttpServlet {
  private final OrdersRepository repository;
  private final MessageSender messageSender;

  @SuppressWarnings("unused")
  public OrderDeliverServlet() {
    this(new OrdersRepository(), MessageSender.getMessageSender());
  }

  public OrderDeliverServlet(OrdersRepository repository, MessageSender messageSender) {
    this.repository = repository;
    this.messageSender = messageSender;
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String pickupMessage = "Your laundry is arriving now.";
    String orderId = request.getParameter("id");
    int id = Integer.parseInt(orderId);

    Order order = repository.find(id);
    order.setStatus("Delivered");
    order.setNotificationStatus("queued");
    repository.update(order);

    String callbackUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "") +
        "/notification/status/update?id=" + order.getId();

    try {
      messageSender.sendSMS(order.getCustomerPhoneNumber(), pickupMessage, callbackUrl);
    } catch (TwilioRestException e) {
      e.printStackTrace();
      throw new ServletException(e.getLocalizedMessage());
    }

    response.sendRedirect("/orders");
  }
}
