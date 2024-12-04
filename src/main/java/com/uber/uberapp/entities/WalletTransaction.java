package com.uber.uberapp.entities;

import com.uber.uberapp.entities.enums.TransactionMethod;
import com.uber.uberapp.entities.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
public class WalletTransaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double amount;

  private TransactionType transactionType;

  private TransactionMethod transactionMethod;

  @OneToOne private Ride ride;

  private String transactionId;

  @ManyToOne private Wallet wallet;

  @CreationTimestamp private LocalDateTime timeStamp;
}
