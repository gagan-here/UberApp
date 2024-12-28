package com.uber.uberapp.services.impl;

import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.repositories.RideRequestRepository;
import com.uber.uberapp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

  private final RideRequestRepository rideRequestRepository;

  @Override
  public RideRequest findRideRequestById(Long rideRequestId) {
    return rideRequestRepository
        .findById(rideRequestId)
        .orElseThrow(
            () -> new ResourceNotFoundException("RideRequest not found with id: " + rideRequestId));
  }

  @Override
  public void update(RideRequest rideRequest) {
    RideRequest existingRideRequest =
        rideRequestRepository
            .findById(rideRequest.getId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "RideRequest not found with id: " + rideRequest.getId()));
    rideRequestRepository.save(existingRideRequest);
  }
}
