package com.uber.uberapp.controllers;

import com.uber.uberapp.dto.RideDto;
import com.uber.uberapp.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {

  private final DriverService driverService;

  @PostMapping("/acceptRide/{rideRequestId}")
  public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId) {
    return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
  }
}