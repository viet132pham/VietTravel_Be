package com.example.be.repository;

import com.example.be.entity.Hotel;
import com.example.be.entity.Location;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
@Repository
public interface HotelRepository extends BaseRepository<Hotel, Long>{
    Hotel findHotelById(long id);

    @Modifying
    @Query(value = "SELECT * \n" +
            "FROM hotel\n" +
            "WHERE sale > 0\n" +
            "ORDER BY sale DESC LIMIT 6;\n;", nativeQuery = true)
    List<Hotel> findSaleHotel();

    List<Hotel> findHotelByLocation(Location location);

    Hotel findHotelByName(String name);

    @Query(value = "SELECT * FROM hotel h WHERE " +
            "(:name IS NULL OR h.name = :name) " +
            "AND (:checkIn IS NULL OR h.time_start <= :checkIn) " +
            "AND (:checkOut IS NULL OR h.time_end <= :checkOut) " +
            "AND (:priceStart IS NULL OR h.price >= :priceStart) " +
            "AND (:priceEnd IS NULL OR h.price <= :priceEnd) " +
            "AND (:sale IS NULL OR h.sale = :sale)", nativeQuery = true)
    Page<Hotel> filterHotels(
            @Param("name") String name,
            @Param("checkIn") Timestamp checkIn,
            @Param("checkOut") Timestamp checkOut,
            @Param("priceStart") Integer priceStart,
            @Param("priceEnd") Integer priceEnd,
            @Param("sale") Integer sale,
            Pageable pageable
    );

    @Query(value = "SELECT DISTINCT h.sale FROM hotel h ORDER BY h.sale ASC", nativeQuery = true)
    List<Integer> getAvailableSales();

}
