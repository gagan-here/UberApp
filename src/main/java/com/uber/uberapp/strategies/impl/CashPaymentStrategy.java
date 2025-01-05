package com.uber.uberapp.strategies.impl;

import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.Payment;
import com.uber.uberapp.entities.enums.PaymentStatus;
import com.uber.uberapp.entities.enums.TransactionMethod;
import com.uber.uberapp.services.PaymentService;
import com.uber.uberapp.services.WalletService;
import com.uber.uberapp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Rider -> 100
// Driver -> 70 Deduct 30rs from Driver's wallet

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

  private final WalletService walletService;
  private final PaymentService paymentService;

  @Override
  public void processPayment(Payment payment) {
    Driver driver = payment.getRide().getDriver();

    double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

    walletService.deductMoneyFromWallet(
        driver.getUser(), platformCommission, null, payment.getRide(), TransactionMethod.RIDE);

    paymentService.updatePaymentStatus(payment, PaymentStatus.CONFIRMED);
  }
}
