package com.uber.uberapp.services.impl;

import com.uber.uberapp.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
  private static final String OSRM_API_BASE_URL =
      "https://router.project-osrm.org/route/v1/driving/";

  @Override
  public double calculateDistance(Point src, Point dest) {

    RestClient.builder()
        .baseUrl(OSRM_API_BASE_URL)
        .build()
        .get()
        .uri("{}, {};{},{}", src.getX(), src.getY(), dest.getY(), dest.getY())
        .retrieve()
        .body();

    return 0;
  }
}
