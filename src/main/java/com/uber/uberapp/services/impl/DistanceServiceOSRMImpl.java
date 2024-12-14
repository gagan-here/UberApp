package com.uber.uberapp.services.impl;

import com.uber.uberapp.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
  @Override
  public double calculateDistance(Point src, Point dest) {
    // TODO Call the third pary api called OSRM to fetch the distance
    return 0;
  }
}
