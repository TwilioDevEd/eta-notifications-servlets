package com.twilio.etanotifications.models;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "customer_phone_number")
  private String customerPhoneNumber;

  @Column(name = "status")
  private String status;

  @Column(name = "notification_status")
  private String notificationStatus;
}
