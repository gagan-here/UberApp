package com.uber.uberapp.services;

import com.uber.uberapp.entities.Payment;
import com.uber.uberapp.entities.Ride;

public interface PaymentService {
  void processPayment(Payment payment);

  Payment createPayment(Ride ride);
}
