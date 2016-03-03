package com.twilio.etanotifications.servlets;

import com.twilio.etanotifications.models.Order;
import com.twilio.etanotifications.repositories.OrdersRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderShowServlet extends HttpServlet {
  private final OrdersRepository repository;

  @SuppressWarnings("unused")
  public OrderShowServlet() {
    this(new OrdersRepository());
  }

  public OrderShowServlet(OrdersRepository repository) {
    this.repository = repository;
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String orderId = request.getParameter("id");
    int id = Integer.parseInt(orderId);

    Order order = repository.find(id);

    request.setAttribute("order", order);
    request.getRequestDispatcher("show.jsp").forward(request, response);
  }
}
