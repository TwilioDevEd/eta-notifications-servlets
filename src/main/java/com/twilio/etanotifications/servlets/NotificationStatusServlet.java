package com.twilio.etanotifications.servlets;

import com.twilio.etanotifications.models.Order;
import com.twilio.etanotifications.repositories.OrdersRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NotificationStatusServlet extends HttpServlet {
  private final OrdersRepository repository;

  @SuppressWarnings("unused")
  public NotificationStatusServlet() {
    this(new OrdersRepository());
  }

  public NotificationStatusServlet(OrdersRepository repository) {
    this.repository = repository;
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String orderId = request.getParameter("id");
    String status = request.getParameter("MessageStatus");
    int id = Integer.parseInt(orderId);

    Order order = repository.find(id);
    order.setNotificationStatus(status);
    repository.update(order);
  }
}
