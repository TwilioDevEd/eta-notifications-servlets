package com.twilio.etanotifications.servlets;

import com.twilio.etanotifications.models.Order;
import com.twilio.etanotifications.repositories.OrdersRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrdersServlet extends HttpServlet {
  private final OrdersRepository repository;

  @SuppressWarnings("unused")
  public OrdersServlet() {
    this(new OrdersRepository());
  }

  public OrdersServlet(OrdersRepository repository) {
    this.repository = repository;
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    Iterable<Order> orders = repository.findAll();

    request.setAttribute("orders", orders);
    request.getRequestDispatcher("orders.jsp").forward(request, response);
  }
}
