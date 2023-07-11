package com.example.be.service;

import com.example.be.dto.VehicleDTO;
import com.example.be.entity.Vehicle;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
public interface VehicleService extends BaseService<Vehicle> {
    List<VehicleDTO> findVehicleTrending();

    List<VehicleDTO> findVehicleByLocation(String location);

    VehicleDTO findVehicleByName(String name);

    Page<VehicleDTO> filterVehicles(Pageable pageable, String name, String checkIn, String checkOut, String priceStart, String priceEnd, String sale);

    VehicleDTO getById(long id);

    Page<VehicleDTO> getListPaginationDTO(Pageable pageable);

    Page<VehicleDTO> getSortedAndPaginateDTO(Pageable pageable);

    List<VehicleDTO> findSaleVehicle();
}
