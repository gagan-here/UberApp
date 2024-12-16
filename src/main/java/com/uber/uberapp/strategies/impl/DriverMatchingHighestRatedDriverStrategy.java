package com.uber.uberapp.strategies.impl;

import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.strategies.DriverMatchingStrategy;
import java.util.List;

public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
  @Override
  public List<Driver> findMatchingDriver(RideRequest rideRequest) {
    return List.of();
  }
}
