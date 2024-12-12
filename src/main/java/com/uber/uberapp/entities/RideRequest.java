package com.uber.uberapp.entities;

import com.uber.uberapp.entities.enums.PaymentMethod;
import com.uber.uberapp.entities.enums.RideRequestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
public class RideRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "Geometry(Point, 4326)")
  private Point pickupLocation;

  @Column(columnDefinition = "Geometry(Point, 4326)")
  private Point dropOffLocation;

  @CreationTimestamp private LocalDateTime requestedTime;

  @ManyToOne(fetch = FetchType.LAZY)
  private Rider rider;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  private RideRequestStatus rideRequestStatus;

  private Double fare;
}
