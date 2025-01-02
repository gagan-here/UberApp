package com.uber.uberapp.services.impl;

import com.uber.uberapp.entities.Payment;
import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
  @Override
  public void processPayment(Payment payment) {}

  @Override
  public Payment createPayment(Ride ride) {
    return null;
  }
}
