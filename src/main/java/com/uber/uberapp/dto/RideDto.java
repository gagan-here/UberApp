package com.uber.uberapp.dto;

import com.uber.uberapp.entities.enums.PaymentMethod;
import com.uber.uberapp.entities.enums.RideStatus;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RideDto {
  private Long id;

  private PointDto pickupLocation;

  private PointDto dropOffLocation;

  private LocalDateTime createdTime;

  private RiderDto rider;

  private DriverDto driver;

  private PaymentMethod paymentMethod;

  private RideStatus rideStatus;

  private String otp;

  private Double fare;

  private LocalDateTime startedAt;

  private LocalDateTime endedAt;
}
