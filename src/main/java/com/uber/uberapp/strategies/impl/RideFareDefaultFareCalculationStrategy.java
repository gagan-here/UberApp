package com.uber.uberapp.strategies.impl;

import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.services.DistanceService;
import com.uber.uberapp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

  private final DistanceService distanceService;

  @Override
  public double calculateFare(RideRequest rideRequest) {
    double distance =
        distanceService.calculateDistance(
            rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());

    return distance * RIDE_FARE_MULTIPLIER;
  }
}
