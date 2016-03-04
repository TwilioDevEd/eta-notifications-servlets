package com.twilio.etanotifications;

import com.twilio.etanotifications.models.Order;
import com.twilio.etanotifications.repositories.OrdersRepository;

public class DBSeeder {
  public static void main(String [] args) {
    OrdersRepository repository = new OrdersRepository();

    Order order1 = new Order("Vincent Vega", "+15551234567");
    Order order2 = new Order("Mia Wallace", "+15451234567");
    repository.create(order1);
    repository.create(order2);
  }
}
