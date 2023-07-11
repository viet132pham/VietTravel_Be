package com.example.be.repository;

import com.example.be.entity.Hotel;
import com.example.be.entity.Location;
import com.example.be.response.TopDestination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LocationRepository extends BaseRepository<Location, Long> {

    @Query(value = "SELECT loc.image AS locationImage, loc.name AS locationName, COUNT(DISTINCT h.id) AS totalHotel, COUNT(DISTINCT t.id) AS totalTour, COUNT(DISTINCT v.id) AS totalVehicle,\n" +
            "       (COUNT(DISTINCT h.id) + COUNT(DISTINCT t.id) + COUNT(DISTINCT v.id)) AS totalAll\n" +
            "FROM location loc\n" +
            "LEFT JOIN tour t ON loc.id = t.location_id\n" +
            "LEFT JOIN hotel h ON loc.id = h.location_id\n" +
            "LEFT JOIN vehicle v ON loc.id = v.location_id\n" +
            "GROUP BY loc.name\n" +
            "ORDER BY totalAll DESC\n" +
            "LIMIT 6", nativeQuery = true)
    List<TopDestination> findTopDestinations();

    Location findLocationByName(String name);

//    @Query(value = "SELECT * FROM Location h WHERE " +
//            "(:name IS NULL OR h.name LIKE %:name%) ", nativeQuery = true)
//    Location findLocationByName(
//            @Param("name") String name
//    );

}
