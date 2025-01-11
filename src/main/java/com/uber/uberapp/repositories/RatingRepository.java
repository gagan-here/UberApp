package com.uber.uberapp.repositories;

import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.Rating;
import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.entities.Rider;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
  List<Rating> findByRider(Rider rider);

  List<Rating> findByDriver(Driver driver);

  Optional<Rating> findByRide(Ride ride);
}
