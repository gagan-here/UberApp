package com.uber.uberapp.strategies.impl;

import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.strategies.RideFareCalculationStrategy;

public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
  @Override
  public double calculateFare(RideRequest rideRequest) {
    return 0;
  }
}
