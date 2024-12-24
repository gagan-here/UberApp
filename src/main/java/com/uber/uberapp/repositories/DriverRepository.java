package com.uber.uberapp.repositories;

import com.uber.uberapp.entities.Driver;
import java.util.List;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Below is spatial functions used in geospatial databases like PostgreSQL with the PostGIS
 * extension <br>
 * <b> ST_Distance(point1, point2) </b> - This function calculates the distance between two
 * geographic points, point1 and point2 <br>
 * <b> ST_DWithin(point1, point2, 10000) </b> - This is a boolean function that checks whether
 * point1 and point2 are within a specified distance
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
  @Query(
      value =
          "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance "
              + "FROM driver d "
              + "WHERE d.available = true AND ST_DWithin(d.curent_location, :pickupLocation, 10000) "
              + "ORDER BY distance "
              + "LIMIT 10",
      nativeQuery = true)
  List<Driver> findTenNearestDrivers(Point pickupLocation);

  @Query(
      value =
          "SELECT d.* "
              + "FROM driver d "
              + "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 15000) "
              + "ORDER BY d.rating DESC "
              + "LIMIT 10",
      nativeQuery = true)
  List<Driver> findTenNearbyTopRatedDrivers(Point pickupLocation);
}
