package com.example.be.service;

import com.example.be.dto.HotelDTO;
import com.example.be.entity.Hotel;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
public interface HotelService extends BaseService<Hotel> {
    List<HotelDTO> findHotelTrending();

    List<HotelDTO> searchHotels(String location);

    HotelDTO findHotelByName(String name);

    Page<HotelDTO> filterHotels(Pageable pageable, String name, String checkIn, String checkOut, String priceStart, String priceEnd, String sale);

    HotelDTO getById(long id);

    Page<HotelDTO> getListPaginationDTO(Pageable pageable);

    Page<HotelDTO> getSortedAndPaginateDTO(Pageable pageable);

    List<HotelDTO> findSaleHotel();
}
