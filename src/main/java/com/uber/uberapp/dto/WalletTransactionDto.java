package com.uber.uberapp.dto;

import com.uber.uberapp.entities.enums.TransactionMethod;
import com.uber.uberapp.entities.enums.TransactionType;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
public class WalletTransactionDto {
  private Long id;

  private Double amount;

  private TransactionType transactionType;

  private TransactionMethod transactionMethod;

  private RideDto ride;

  private String transactionId;

  @ManyToOne private WalletDto wallet;

  @CreationTimestamp private LocalDateTime timeStamp;
}
