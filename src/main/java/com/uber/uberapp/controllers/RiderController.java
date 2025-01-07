package com.uber.uberapp.controllers;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RatingDto;
import com.uber.uberapp.dto.RideDto;
import com.uber.uberapp.dto.RideRequestDto;
import com.uber.uberapp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/riders")
@RequiredArgsConstructor
public class RiderController {

  private final RiderService riderService;

  @PostMapping("/requestRide")
  public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
    return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
  }

  @PostMapping("/cancelRide/{rideId}")
  public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId) {
    return ResponseEntity.ok(riderService.cancelRide(rideId));
  }

  @PostMapping("/rateDriver")
  public ResponseEntity<DriverDto> rateDriver(@RequestBody RatingDto ratingDto) {
    return ResponseEntity.ok(riderService.rateDriver(ratingDto.getRideId(), ratingDto.getRating()));
  }
}
