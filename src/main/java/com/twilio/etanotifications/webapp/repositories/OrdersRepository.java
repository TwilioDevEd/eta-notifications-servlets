package com.twilio.etanotifications.webapp.repositories;

import com.twilio.etanotifications.webapp.models.Order;

public class OrdersRepository extends Repository<Order> {

    public OrdersRepository() {
        super(Order.class);
    }
}
