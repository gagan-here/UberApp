package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RideDto;
import com.uber.uberapp.dto.RiderDto;
import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.entities.enums.RideRequestStatus;
import com.uber.uberapp.entities.enums.RideStatus;
import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.repositories.DriverRepository;
import com.uber.uberapp.services.DriverService;
import com.uber.uberapp.services.RideRequestService;
import com.uber.uberapp.services.RideService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

  private final RideRequestService rideRequestService;
  private final DriverRepository driverRepository;
  private final RideService rideService;
  private final ModelMapper modelMapper;

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
    currentDriver.setAvailable(false);
    Driver savedDriver = driverRepository.save(currentDriver);

    Ride ride = rideService.createNewRide(rideRequest, savedDriver);
    return modelMapper.map(ride, RideDto.class);
  }

  @Override
  public RideDto cancelRide(Long rideId) {
    return null;
  }

  @Override
  public RideDto startRide(Long rideId, String otp) {
    Ride ride = rideService.getRideById(rideId);
    Driver driver = getCurrentDriver();

    if (!driver.equals(ride.getDriver())) {
      throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
    }

    if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
      throw new RuntimeException(
          "Ride status is not CONFIRMED hence cannot be started, status: " + ride.getRideStatus());
    }

    if (!otp.equals(ride.getOtp())) {
      throw new RuntimeException("Otp is not valid, otp: " + otp);
    }

    ride.setStartedAt(LocalDateTime.now());
    Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
    return modelMapper.map(savedRide, RideDto.class);
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
