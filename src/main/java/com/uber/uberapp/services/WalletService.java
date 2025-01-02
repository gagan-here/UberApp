package com.uber.uberapp.services;

import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.entities.User;
import com.uber.uberapp.entities.Wallet;
import com.uber.uberapp.entities.enums.TransactionMethod;

public interface WalletService {

  Wallet addMoneyToWallet(
      User user,
      Double amount,
      String transactionId,
      Ride ride,
      TransactionMethod transactionMethod);

  Wallet deductMoneyFromWallet(
      User user,
      Double amount,
      String transactionId,
      Ride ride,
      TransactionMethod transactionMethod);

  void withdrawAllMyMoneyFromWallet();

  Wallet findWalletById(Long walletId);

  Wallet createNewWallet(User user);

  Wallet findByUser(User user);
}
