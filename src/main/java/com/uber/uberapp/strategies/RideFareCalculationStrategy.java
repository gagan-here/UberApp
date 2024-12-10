package com.uber.uberapp.strategies;

import com.uber.uberapp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {

  double calculateFare(RideRequestDto rideRequestDto);
}
