package com.twilio.etanotifications.repositories;

import com.twilio.etanotifications.models.Order;

public class OrdersRepository extends Repository<Order> {

  public OrdersRepository() {
    super(Order.class);
  }
}
