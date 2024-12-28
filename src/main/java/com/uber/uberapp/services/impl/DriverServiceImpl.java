package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RideDto;
import com.uber.uberapp.dto.RiderDto;
import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.entities.enums.RideRequestStatus;
import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.repositories.DriverRepository;
import com.uber.uberapp.services.DriverService;
import com.uber.uberapp.services.RideRequestService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

  private final RideRequestService rideRequestService;
  private final DriverRepository driverRepository;

  @Override
  @Transactional
  public RideDto acceptRide(Long rideRequestId) {
    RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
    if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
      throw new RuntimeException(
          "RideRequest cannot be accepted, status is " + rideRequest.getRideRequestStatus());
    }

    Driver currentDriver = getCurrentDriver();
    if (Boolean.FALSE.equals(currentDriver.getAvailable())) {
      throw new RuntimeException("Driver cannot accept ride due to unavaliability");
    }

    rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

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

  @Override
  public Driver getCurrentDriver() {
    return driverRepository
        .findById(2L)
        .orElseThrow(() -> new ResourceNotFoundException("Drive not found with id " + 2));
  }
}
