package com.example.be.service;

import com.example.be.dto.TourDTO;
import com.example.be.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface TourService extends BaseService<Tour> {

    List<TourDTO> findTourTrending();
    List<TourDTO> findTourByLocation(String location);

    TourDTO findTourByName(String name);

    Page<TourDTO> filterTours(Pageable pageable, String name, String checkIn, String checkOut, String priceStart, String priceEnd, String sale);
    Page<TourDTO> getListPaginationDTO(Pageable pageable);

    Page<TourDTO> getSortedAndPaginateDTO(Pageable pageable);

    TourDTO getById(long id);

    List<TourDTO> findSaleTour();
}
