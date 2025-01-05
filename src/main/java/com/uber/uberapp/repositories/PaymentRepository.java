package com.uber.uberapp.repositories;

import com.uber.uberapp.entities.Payment;
import com.uber.uberapp.entities.Ride;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
  Optional<Payment> findByRide(Ride ride);
}
