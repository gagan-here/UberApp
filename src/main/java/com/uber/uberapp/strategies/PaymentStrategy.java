package com.uber.uberapp.strategies;

import com.uber.uberapp.entities.Payment;

public interface PaymentStrategy {
  Double PLATFORM_COMMISSION = 0.3;

  void processPayment(Payment payment);
}
