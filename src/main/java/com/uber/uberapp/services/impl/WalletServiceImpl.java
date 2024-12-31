package com.uber.uberapp.services.impl;

import com.uber.uberapp.entities.User;
import com.uber.uberapp.entities.Wallet;
import com.uber.uberapp.repositories.WalletTransactionRepository;
import com.uber.uberapp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

  private final WalletTransactionRepository walletTransactionRepository;

  @Override
  public Wallet addMoneyToWallet(Long userId, Double amount) {
    return null;
  }

  @Override
  public void withdrawAllMyMoneyFromWallet() {}

  @Override
  public Wallet findWalletById(Long walletId) {
    return null;
  }

  @Override
  public Wallet createNewWallet(User user) {
    return null;
  }
}
