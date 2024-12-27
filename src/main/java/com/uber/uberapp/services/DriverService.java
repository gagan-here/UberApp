package com.uber.uberapp.services;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RideDto;
import com.uber.uberapp.dto.RiderDto;
import java.util.List;

public interface DriverService {
  RideDto acceptRide(Long rideRequestId);

  RideDto cancelRide(Long rideId);

  RideDto startRide(Long rideId);

  RideDto endRide(Long rideId);

  RiderDto rateRider(Long rideId, Integer rating);

  DriverDto getMyProfile();

  List<RideDto> getAllMyRides();
}
