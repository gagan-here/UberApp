package com.uber.uberapp.repositories;

import com.uber.uberapp.entities.User;
import com.uber.uberapp.entities.Wallet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
  Optional<Wallet> findByUser(User user);
}
