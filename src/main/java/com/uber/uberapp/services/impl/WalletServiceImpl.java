package com.uber.uberapp.services.impl;

import com.uber.uberapp.entities.User;
import com.uber.uberapp.entities.Wallet;
import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.repositories.WalletRepository;
import com.uber.uberapp.repositories.WalletTransactionRepository;
import com.uber.uberapp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

  private final WalletTransactionRepository walletTransactionRepository;
  private final WalletRepository walletRepository;

  @Override
  public Wallet addMoneyToWallet(User user, Double amount) {
    Wallet wallet =
        walletRepository
            .findByUser(user)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Wallet not found for user with id: " + user.getId()));
    wallet.setBalance(wallet.getBalance() + amount);
    return walletRepository.save(wallet);
  }

  @Override
  public void withdrawAllMyMoneyFromWallet() {}

  @Override
  public Wallet findWalletById(Long walletId) {
    return walletRepository
        .findById(walletId)
        .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));
  }

  @Override
  public Wallet createNewWallet(User user) {
    Wallet wallet = new Wallet();
    wallet.setUser(user);
    return walletRepository.save(wallet);
  }
}
