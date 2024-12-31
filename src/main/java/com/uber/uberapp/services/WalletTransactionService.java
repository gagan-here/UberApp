package com.uber.uberapp.services;

import com.uber.uberapp.dto.WalletTransactionDto;

public interface WalletTransactionService {

  void createNewWalletTransaction(WalletTransactionDto walletTransactionDto);
}
