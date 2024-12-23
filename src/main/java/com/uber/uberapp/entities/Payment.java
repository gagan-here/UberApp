package com.uber.uberapp.entities;

import com.uber.uberapp.entities.enums.PaymentMethod;
import com.uber.uberapp.entities.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @OneToOne(fetch = FetchType.LAZY)
  private Ride ride;

  private Double amount;

  @Enumerated(EnumType.STRING)
  private PaymentStatus paymentStatus;

  @CreationTimestamp private LocalDateTime paymentTime;
}
