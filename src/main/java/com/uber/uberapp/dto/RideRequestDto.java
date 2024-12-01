package com.uber.uberapp.dto;

import com.uber.uberapp.entities.enums.PaymentMethod;
import com.uber.uberapp.entities.enums.RideRequestStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

  private Long id;

  private Point pickupLocation;

  private Point dropOffLocation;

  private LocalDateTime requestedTime;

  private RiderDto rider;

  private PaymentMethod paymentMethod;

  private RideRequestStatus rideRequestStatus;
}
