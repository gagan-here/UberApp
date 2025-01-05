package com.uber.uberapp.services.impl;

import com.uber.uberapp.entities.Payment;
import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.entities.enums.PaymentStatus;
import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.repositories.PaymentRepository;
import com.uber.uberapp.services.PaymentService;
import com.uber.uberapp.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentStrategyManager paymentStrategyManager;

  @Override
  public void processPayment(Ride ride) {
    Payment payment =
        paymentRepository
            .findByRide(ride)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Payment not found for ride with id: " + ride.getId()));
    paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
  }

  @Override
  public Payment createNewPayment(Ride ride) {
    Payment payment =
        Payment.builder()
            .ride(ride)
            .paymentMethod(ride.getPaymentMethod())
            .amount(ride.getFare())
            .paymentStatus(PaymentStatus.PENDING)
            .build();
    return paymentRepository.save(payment);
  }

  @Override
  public void updatePaymentStatus(Payment payment, PaymentStatus status) {
    payment.setPaymentStatus(status);
    paymentRepository.save(payment);
  }
}
