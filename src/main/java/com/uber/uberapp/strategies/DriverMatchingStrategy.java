package com.uber.uberapp.strategies;

import com.uber.uberapp.dto.RideRequestDto;
import com.uber.uberapp.entities.Driver;
import java.util.List;

public interface DriverMatchingStrategy {

  List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);
}
