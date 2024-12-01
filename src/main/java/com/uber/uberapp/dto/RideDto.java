package com.uber.uberapp.dto;

import com.uber.uberapp.entities.enums.PaymentMethod;
import com.uber.uberapp.entities.enums.RideStatus;
import java.time.LocalDateTime;
import org.locationtech.jts.geom.Point;

public class RideDto {
  private Long id;

  private Point pickupLocation;

  private Point dropOffLocation;

  private LocalDateTime createdTime;

  private RiderDto rider;

  private DriverDto driver;

  private PaymentMethod paymentMethod;

  private RideStatus rideStatus;

  private Double fare;

  private LocalDateTime startedAt;

  private LocalDateTime endedAt;
}
