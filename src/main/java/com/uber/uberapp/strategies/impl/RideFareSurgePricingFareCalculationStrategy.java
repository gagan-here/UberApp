package com.uber.uberapp.strategies.impl;

import com.uber.uberapp.dto.RideRequestDto;
import com.uber.uberapp.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
  @Override
  public double calculateFare(RideRequestDto rideRequestDto) {
    return 0;
  }
}
