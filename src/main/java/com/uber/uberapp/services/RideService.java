package com.uber.uberapp.services;

import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.entities.Rider;
import com.uber.uberapp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

  Ride getRideById(Long rideId);

  Ride createNewRide(RideRequest rideRequest, Driver driver);

  Ride updateRideStatus(Ride ride, RideStatus rideStatus);

  Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

  Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
