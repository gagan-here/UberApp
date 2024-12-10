package com.uber.uberapp.strategies.impl;

import com.uber.uberapp.dto.RideRequestDto;
import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.strategies.DriverMatchingStrategy;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
  @Override
  public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
    return List.of();
  }
}
