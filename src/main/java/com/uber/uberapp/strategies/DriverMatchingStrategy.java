package com.uber.uberapp.strategies;

import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.RideRequest;
import java.util.List;

public interface DriverMatchingStrategy {

  List<Driver> findMatchingDriver(RideRequest rideRequest);
}
