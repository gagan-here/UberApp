package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RideDto;
import com.uber.uberapp.dto.RideRequestDto;
import com.uber.uberapp.dto.RiderDto;
import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.entities.Rider;
import com.uber.uberapp.entities.User;
import com.uber.uberapp.entities.enums.RideRequestStatus;
import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.repositories.RideRequestRepository;
import com.uber.uberapp.repositories.RiderRepository;
import com.uber.uberapp.services.RiderService;
import com.uber.uberapp.strategies.RideStrategyManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

  private final ModelMapper modelMapper;
  private final RideStrategyManager rideStrategyManager;
  private final RideRequestRepository rideRequestRepository;
  private final RiderRepository riderRepository;

  @Override
  public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
    Rider rider = getCurrentRider();
    RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
    rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

    Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
    rideRequest.setFare(fare);

    RideRequest saveRideRequest = rideRequestRepository.save(rideRequest);

    rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);

    return modelMapper.map(saveRideRequest, RideRequestDto.class);
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

  @Override
  public Rider createNewRider(User user) {
    Rider rider = Rider.builder().user(user).rating(0.0).build();
    return riderRepository.save(rider);
  }

  @Override
  public Rider getCurrentRider() {
    // TODO : implement Spring security
    return riderRepository
        .findById(1l)
        .orElseThrow(() -> new ResourceNotFoundException("Rider not found with id: " + 1));
  }
}
