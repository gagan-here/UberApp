package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RideDto;
import com.uber.uberapp.dto.RideRequestDto;
import com.uber.uberapp.dto.RiderDto;
import com.uber.uberapp.services.RiderService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RiderServiceImpl implements RiderService {
  @Override
  public RideRequestDto requestRide(RideRequestDto rideId) {
    return null;
  }

  @Override
  public RideDto cancelRide(Long rideId) {
    return null;
  }

  @Override
  public DriverDto rateDriver(Long rideId, Integer rating) {
    return null;
  }

  @Override
  public RiderDto getMyProfile() {
    return null;
  }

  @Override
  public List<RideDto> getAllMyRides() {
    return List.of();
  }
}
