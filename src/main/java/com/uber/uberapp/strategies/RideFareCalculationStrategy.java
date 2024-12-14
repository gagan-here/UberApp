package com.uber.uberapp.strategies;

import com.uber.uberapp.entities.RideRequest;

public interface RideFareCalculationStrategy {

  double RIDE_FARE_MULTIPLIER = 10;

  double calculateFare(RideRequest rideRequest);
}
