package com.uber.uberapp.strategies.impl;

import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.Payment;
import com.uber.uberapp.entities.Rider;
import com.uber.uberapp.entities.enums.TransactionMethod;
import com.uber.uberapp.services.WalletService;
import com.uber.uberapp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Rider had 232 money in his wallet, Driver had 500 money in his wallet
// Ride cost is 100, commission = 30
// Rider -> 232 - 100 = 132 (remaining money in rider's wallet)
// Driver -> 500 + (100 - 30) = 570
@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
  private final WalletService walletService;

  @Override
  @Transactional
  public void processPayment(Payment payment) {
    Driver driver = payment.getRide().getDriver();
    Rider rider = payment.getRide().getRider();

    walletService.deductMoneyFromWallet(
        rider.getUser(), payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);

    double driversCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);

    walletService.addMoneyToWallet(
        driver.getUser(), driversCut, null, payment.getRide(), TransactionMethod.RIDE);
  }
}
