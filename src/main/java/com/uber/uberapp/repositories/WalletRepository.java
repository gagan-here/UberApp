package com.uber.uberapp.repositories;

import com.uber.uberapp.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {}
