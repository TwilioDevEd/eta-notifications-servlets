package com.twilio.etanotifications.servlets;

import com.twilio.etanotifications.lib.MessageSender;
import com.twilio.etanotifications.models.Order;
import com.twilio.etanotifications.repositories.OrdersRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderPickupServlet extends HttpServlet {
  private final OrdersRepository repository;
  private final MessageSender messageSender;

  @SuppressWarnings("unused")
  public OrderPickupServlet() {
    this(new OrdersRepository(), MessageSender.getMessageSender());
  }

  public OrderPickupServlet(OrdersRepository repository, MessageSender messageSender) {
    this.repository = repository;
    this.messageSender = messageSender;
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String pickupMessage = "Your laundry is done and on its way to you!";
    String orderId = request.getParameter("id");
    int id = Integer.parseInt(orderId);

    Order order = repository.find(id);
    order.setStatus("Shipped");
    order.setNotificationStatus("queued");
    order = repository.update(order);

    String callbackUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "") +
        "/notification/status/update?id=" + order.getId();

    int success = 0;
    try {
      success = messageSender.sendSMS(order.getCustomerPhoneNumber(), pickupMessage, callbackUrl);
    } catch (Exception e) {
      throw new ServletException(e.getLocalizedMessage());
    }

    if (success == 0) {
      response.sendRedirect(String.format("/order?id=%d", id));
    } else {
      throw new ServletException(
          String.format("An error occurred while sending the SMS. Error code: %d", success));
    }
  }
}
