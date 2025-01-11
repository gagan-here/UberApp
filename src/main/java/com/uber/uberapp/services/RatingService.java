package com.uber.uberapp.services;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RiderDto;
import com.uber.uberapp.entities.Ride;

public interface RatingService {
  DriverDto rateDriver(Ride ride, Integer rating);

  RiderDto rateRider(Ride ride, Integer rating);

  void createNewRaing(Ride ride);
}
