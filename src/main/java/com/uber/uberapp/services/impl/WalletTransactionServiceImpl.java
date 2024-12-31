package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.WalletTransactionDto;
import com.uber.uberapp.entities.WalletTransaction;
import com.uber.uberapp.repositories.WalletTransactionRepository;
import com.uber.uberapp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

  private final WalletTransactionRepository walletTransactionRepository;
  private final ModelMapper modelMapper;

  @Override
  public void createNewWalletTransaction(WalletTransactionDto walletTransactionDto) {
    WalletTransaction walletTransaction =
        modelMapper.map(walletTransactionDto, WalletTransaction.class);
    walletTransactionRepository.save(walletTransaction);
  }
}
