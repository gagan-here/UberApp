package com.uber.uberapp.entities;

import com.uber.uberapp.entities.enums.PaymentMethod;
import com.uber.uberapp.entities.enums.RideStatus;
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
public class Ride {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "Geometry(Point, 4326)")
  private Point pickupLocation;

  @Column(columnDefinition = "Geometry(Point, 4326)")
  private Point dropOffLocation;

  @CreationTimestamp private LocalDateTime createdTime;

  @ManyToOne(fetch = FetchType.LAZY)
  private Rider rider;

  @ManyToOne(fetch = FetchType.LAZY)
  private Driver driver;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  private RideStatus rideStatus;

  private Double fare;

  private LocalDateTime startedAt;

  private LocalDateTime endedAt;
}