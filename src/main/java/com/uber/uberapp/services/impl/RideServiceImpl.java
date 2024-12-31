package com.uber.uberapp.services.impl;

import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.entities.Rider;
import com.uber.uberapp.entities.enums.RideRequestStatus;
import com.uber.uberapp.entities.enums.RideStatus;
import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.repositories.RideRepository;
import com.uber.uberapp.services.RideRequestService;
import com.uber.uberapp.services.RideService;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

  private final RideRepository rideRepository;
  private final RideRequestService rideRequestService;
  private final ModelMapper modelMapper;

  @Override
  public Ride getRideById(Long rideId) {
    return rideRepository
        .findById(rideId)
        .orElseThrow(() -> new ResourceNotFoundException("Ride not found with id: " + rideId));
  }

  @Override
  public Ride createNewRide(RideRequest rideRequest, Driver driver) {
    rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

    Ride ride = modelMapper.map(rideRequest, Ride.class);
    ride.setRideStatus(RideStatus.CONFIRMED);
    ride.setDriver(driver);
    ride.setOtp(generateRandomOtp());
    ride.setId(null);

    rideRequestService.update(rideRequest);
    return rideRepository.save(ride);
  }

  @Override
  public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
    ride.setRideStatus(rideStatus);
    return rideRepository.save(ride);
  }

  @Override
  public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
    return rideRepository.findByRider(rider, pageRequest);
  }

  @Override
  public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
    return rideRepository.findByDriver(driver, pageRequest);
  }

  private String generateRandomOtp() {
    Random random = new Random();
    int otpInt = random.nextInt(10000); // generates number from 0 to 9999
    return String.format("%04d", otpInt);
  }
}
