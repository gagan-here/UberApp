package com.uber.uberapp.strategies;

import com.uber.uberapp.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.uber.uberapp.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.uber.uberapp.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.uber.uberapp.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

  private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
  private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;

  private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
  private final RideFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;

  public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
    if (riderRating >= 4.0) {
      return highestRatedDriverStrategy;
    } else {
      return nearestDriverStrategy;
    }
  }

  public RideFareCalculationStrategy rideFareCalculationStrategy() {
    // 6PM to 9PM is SURGE TIME
    LocalTime surgeStartTime = LocalTime.of(18, 0);
    LocalTime surgeEndTime = LocalTime.of(21, 0);
    LocalTime currentTime = LocalTime.now();

    boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

    if (isSurgeTime) {
      return surgePricingFareCalculationStrategy;
    } else {
      return defaultFareCalculationStrategy;
    }
  }
}
