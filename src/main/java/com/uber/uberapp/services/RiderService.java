package com.uber.uberapp.services;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RideDto;
import com.uber.uberapp.dto.RideRequestDto;
import com.uber.uberapp.dto.RiderDto;
import com.uber.uberapp.entities.Rider;
import com.uber.uberapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {
  RideRequestDto requestRide(RideRequestDto rideId);

  RideDto cancelRide(Long rideId);

  DriverDto rateDriver(Long rideId, Integer rating);

  RiderDto getMyProfile();

  Page<RideDto> getAllMyRides(PageRequest pageRequest);

  Rider createNewRider(User user);

  Rider getCurrentRider();
}
