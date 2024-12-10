package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RideDto;
import com.uber.uberapp.dto.RideRequestDto;
import com.uber.uberapp.dto.RiderDto;
import com.uber.uberapp.entities.RideRequest;
import com.uber.uberapp.services.RiderService;
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

  @Override
  public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
    RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
    log.info(rideRequest.toString());
    return null;
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
}
