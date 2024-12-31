package com.uber.uberapp.repositories;

import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
  Page<Ride> findByRider(Rider rider, PageRequest pageRequest);

  Page<Ride> findByDriver(Driver driver, PageRequest pageRequest);
}
