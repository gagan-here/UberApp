package com.uber.uberapp.services;

import com.uber.uberapp.entities.RideRequest;

public interface RideRequestService {

  RideRequest findRideRequestById(Long rideRequestId);

  void update(RideRequest rideRequest);
}
