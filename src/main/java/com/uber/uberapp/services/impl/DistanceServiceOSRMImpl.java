package com.uber.uberapp.services.impl;

import com.uber.uberapp.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
  @Override
  public double calculateDistance(Point src, Point dest) {
    return 0;
  }
}