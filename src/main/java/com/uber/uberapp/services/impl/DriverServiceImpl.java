package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RideDto;
import com.uber.uberapp.dto.RiderDto;
import com.uber.uberapp.services.DriverService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {
  @Override
  public RideDto acceptRide(Long rideId) {
    return null;
  }

  @Override
  public RideDto cancelRide(Long rideId) {
    return null;
  }

  @Override
  public RideDto startRide(Long rideId) {
    return null;
  }

  @Override
  public RideDto endRide(Long rideId) {
    return null;
  }

  @Override
  public RiderDto rateRider(Long rideId, Integer rating) {
    return null;
  }

  @Override
  public DriverDto getMyProfile() {
    return null;
  }

  @Override
  public List<RideDto> getAllMyRides() {
    return List.of();
  }
}
