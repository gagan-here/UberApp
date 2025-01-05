package com.uber.uberapp.strategies;

import com.uber.uberapp.entities.enums.PaymentMethod;
import com.uber.uberapp.strategies.impl.CashPaymentStrategy;
import com.uber.uberapp.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

  private final WalletPaymentStrategy walletPaymentStrategy;
  private final CashPaymentStrategy cashPaymentStrategy;

  public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
    return switch (paymentMethod) {
      case WALLET -> walletPaymentStrategy;
      case CASH -> cashPaymentStrategy;
    };
  }
}
