package com.uber.uberapp.dto;

import com.uber.uberapp.entities.enums.PaymentMethod;
import com.uber.uberapp.entities.enums.RideRequestStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

  private Long id;

  private PointDto pickupLocation;

  private PointDto dropOffLocation;

  private PaymentMethod paymentMethod;

  private LocalDateTime requestedTime;

  private RiderDto rider;

  private Double fare;

  private RideRequestStatus rideRequestStatus;
}
