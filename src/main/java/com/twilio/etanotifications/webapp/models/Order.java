package com.twilio.etanotifications.webapp.models;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
}
