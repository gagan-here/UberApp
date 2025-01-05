package com.uber.uberapp.services;

import com.uber.uberapp.entities.Payment;
import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.entities.enums.PaymentStatus;

public interface PaymentService {
  void processPayment(Ride ride);

  Payment createNewPayment(Ride ride);

  void updatePaymentStatus(Payment payment, PaymentStatus status);
}
