package com.uber.uberapp.repositories;

import com.uber.uberapp.entities.Rider;
import com.uber.uberapp.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
  Optional<Rider> findByUser(User user);
}
