package com.uber.uberapp.services;

import com.uber.uberapp.dto.RideRequestDto;
import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

  Ride getRideById(Long rideId);

  void matchWithDrivers(RideRequestDto rideRequestDto);

  Ride createNewRide(RideRequest rideRequest, Driver driver);

  Ride updateRideStatus(Long rideId, RideStatus rideStatus);

  Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

  Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}
