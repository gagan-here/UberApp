package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RideDto;
import com.uber.uberapp.dto.RiderDto;
import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.entities.User;
import com.uber.uberapp.entities.enums.RideRequestStatus;
import com.uber.uberapp.entities.enums.RideStatus;
import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.repositories.DriverRepository;
import com.uber.uberapp.services.DriverService;
import com.uber.uberapp.services.PaymentService;
import com.uber.uberapp.services.RatingService;
import com.uber.uberapp.services.RideRequestService;
import com.uber.uberapp.services.RideService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

  private final RideRequestService rideRequestService;
  private final RideService rideService;
  private final PaymentService paymentService;
  private final RatingService ratingService;

  private final DriverRepository driverRepository;

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
    Driver savedDriver = updateDriverAvailability(currentDriver, false);

    Ride ride = rideService.createNewRide(rideRequest, savedDriver);
    return modelMapper.map(ride, RideDto.class);
  }

  @Override
  public RideDto cancelRide(Long rideId) {
    Ride ride = rideService.getRideById(rideId);

    Driver driver = getCurrentDriver();

    if (!driver.equals(ride.getDriver())) {
      throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
    }

    if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
      throw new RuntimeException(
          "Ride cannot be cancelled, invalid status: " + ride.getRideStatus());
    }

    rideService.updateRideStatus(ride, RideStatus.CANCELLED);
    updateDriverAvailability(driver, true);

    return modelMapper.map(ride, RideDto.class);
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

    paymentService.createNewPayment(savedRide);
    ratingService.createNewRaing(savedRide);

    return modelMapper.map(savedRide, RideDto.class);
  }

  @Override
  @Transactional
  public RideDto endRide(Long rideId) {
    Ride ride = rideService.getRideById(rideId);
    Driver driver = getCurrentDriver();

    if (!driver.equals(ride.getDriver())) {
      throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
    }

    if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
      throw new RuntimeException(
          "Ride status is not ONGOING hence cannot be ended, status: " + ride.getRideStatus());
    }

    ride.setEndedAt(LocalDateTime.now());
    Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
    updateDriverAvailability(driver, true);

    paymentService.processPayment(ride);

    return modelMapper.map(savedRide, RideDto.class);
  }

  @Override
  public RiderDto rateRider(Long rideId, Integer rating) {
    Ride ride = rideService.getRideById(rideId);
    Driver driver = getCurrentDriver();

    if (!driver.equals(ride.getDriver())) {
      throw new RuntimeException("Driver is not the owner of this Ride");
    }

    if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
      throw new RuntimeException(
          "Ride status is not Ended hence cannot start rating, status: " + ride.getRideStatus());
    }

    return ratingService.rateRider(ride, rating);
  }

  @Override
  public DriverDto getMyProfile() {
    Driver currentDriver = getCurrentDriver();
    return modelMapper.map(currentDriver, DriverDto.class);
  }

  @Override
  public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
    Driver currentDriver = getCurrentDriver();
    return rideService
        .getAllRidesOfDriver(currentDriver, pageRequest)
        .map(ride -> modelMapper.map(ride, RideDto.class));
  }

  @Override
  public Driver getCurrentDriver() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return driverRepository
        .findByUser(user)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Driver not associated with user id " + user.getId()));
  }

  @Override
  public Driver updateDriverAvailability(Driver driver, boolean available) {
    driver.setAvailable(available);
    return driverRepository.save(driver);
  }

  @Override
  public Driver createNewDriver(Driver driver) {
    return driverRepository.save(driver);
  }
}
