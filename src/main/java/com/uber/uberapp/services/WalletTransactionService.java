package com.uber.uberapp.services;

import com.uber.uberapp.entities.WalletTransaction;

public interface WalletTransactionService {

  void createNewWalletTransaction(WalletTransaction walletTransaction);
}
