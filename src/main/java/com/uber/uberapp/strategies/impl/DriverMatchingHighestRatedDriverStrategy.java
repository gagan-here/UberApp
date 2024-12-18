package com.uber.uberapp.strategies.impl;

import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.repositories.DriverRepository;
import com.uber.uberapp.strategies.DriverMatchingStrategy;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {

  private final DriverRepository driverRepository;

  @Override
  public List<Driver> findMatchingDriver(RideRequest rideRequest) {
    return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
  }
}
