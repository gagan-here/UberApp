package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.RiderDto;
import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.Rating;
import com.uber.uberapp.entities.Ride;
import com.uber.uberapp.entities.Rider;
import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.exceptions.RunTimeConflictException;
import com.uber.uberapp.repositories.DriverRepository;
import com.uber.uberapp.repositories.RatingRepository;
import com.uber.uberapp.repositories.RiderRepository;
import com.uber.uberapp.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

  private final RatingRepository ratingRepository;
  private final DriverRepository driverRepository;
  private final RiderRepository riderRepository;
  private final ModelMapper modelMapper;

  @Override
  public DriverDto rateDriver(Ride ride, Integer rating) {
    Driver driver = ride.getDriver();
    Rating ratingObj =
        ratingRepository
            .findByRide(ride)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Rating not found for ride with id: " + ride.getId()));

    if (ratingObj.getDriverRating() != null)
      throw new RunTimeConflictException("Driver has already been rated, cannot be rated again!");

    ratingObj.setDriverRating(rating);

    ratingRepository.save(ratingObj);

    Double newRating =
        ratingRepository.findByDriver(driver).stream()
            .mapToDouble(Rating::getDriverRating)
            .average()
            .orElse(0.0);
    driver.setRating(newRating);

    Driver savedDriver = driverRepository.save(driver);
    return modelMapper.map(savedDriver, DriverDto.class);
  }

  @Override
  public RiderDto rateRider(Ride ride, Integer rating) {
    Rider rider = ride.getRider();
    Rating ratingObj =
        ratingRepository
            .findByRide(ride)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Rating not found for ride with id: " + ride.getId()));
    if (ratingObj.getDriverRating() != null)
      throw new RunTimeConflictException("Rider has already been rated, cannot be rated again!");

    ratingObj.setRiderRating(rating);

    ratingRepository.save(ratingObj);

    Double newRating =
        ratingRepository.findByRider(rider).stream()
            .mapToDouble(Rating::getRiderRating)
            .average()
            .orElse(0.0);
    rider.setRating(newRating);

    Rider savedRider = riderRepository.save(rider);
    return modelMapper.map(savedRider, RiderDto.class);
  }

  @Override
  public void createNewRaing(Ride ride) {
    Rating rating =
        Rating.builder().rider(ride.getRider()).driver(ride.getDriver()).ride(ride).build();
    ratingRepository.save(rating);
  }
}
