package com.uber.uberapp.services;

import com.uber.uberapp.entities.User;
import com.uber.uberapp.entities.Wallet;

public interface WalletService {

  Wallet addMoneyToWallet(Long userId, Double amount);

  void withdrawAllMyMoneyFromWallet();

  Wallet findWalletById(Long walletId);

  Wallet createNewWallet(User user);
}
