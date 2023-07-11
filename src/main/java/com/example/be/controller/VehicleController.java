package com.example.be.controller;

import com.example.be.dto.TourDTO;
import com.example.be.dto.VehicleDTO;
import com.example.be.entity.Vehicle;
import com.example.be.repository.VehicleRepository;
import com.example.be.service.BaseService;
import com.example.be.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/vehicle")
public class VehicleController extends BaseController<Vehicle> {
    public VehicleController(BaseService<Vehicle> baseService) {
        super(baseService);
    }

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/find/trending")
    public List<VehicleDTO> findCategoryIdInCartitem() {
        return vehicleService.findVehicleTrending();
    }

    @GetMapping("/find/top_deal")
    public List<VehicleDTO> findSaleVehicle() {
        return vehicleService.findSaleVehicle();
    }

    @GetMapping("/get/sale_value")
    public List<Integer> findSaleValue() {
        return vehicleRepository.getAvailableSales();
    }

    @GetMapping("/search/{location}")
    public List<VehicleDTO> searchTour(@PathVariable(value = "location") String location) {
        return vehicleService.findVehicleByLocation(location);
    }

    @GetMapping("/search_by_name/{name}")
    public VehicleDTO searchVehicleByName(@PathVariable(value = "name") String name) {
        return vehicleService.findVehicleByName(name);
    }

    @GetMapping("/filter")
    public Page<VehicleDTO> filterHotels(
            @RequestParam(value = "location", required = false) String name,
            @RequestParam(value = "checkIn", required = false) String checkIn,
            @RequestParam(value = "checkOut", required = false) String checkOut,
            @RequestParam(value = "priceStart", required = false) String priceStart,
            @RequestParam(value = "priceEnd", required = false) String priceEnd,
            @RequestParam(value = "sale", required = false) String sale,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "pageNumber", required = true) int pageNumber,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        Sort.Direction sortDirection = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        if (sortBy == null) {
            sortBy = "id";
        }

        Sort sort = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        if(name == null && checkIn == null && checkOut == null && priceStart == null && priceEnd == null && sale == null) {
            return vehicleService.getListPaginationDTO(pageable);
        } else {
            return vehicleService.filterVehicles(pageable, name, checkIn, checkOut, priceStart, priceEnd, sale);
        }
    }

    @GetMapping("/sort_dto")
    public Page<VehicleDTO> getSortedAndPaginateDTO(
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "pageNumber", required = false) int pageNumber,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {

        Sort.Direction sortDirection = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        if (sortBy == null) {
            sortBy = "id";
        }

        Sort sort = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return vehicleService.getSortedAndPaginateDTO(pageable);
    }

    @GetMapping("/list_pagination_dto")
    public Page<VehicleDTO> getListPaginationDTO(@RequestParam(value = "pageNumber",required = true) int pageNumber,
                                              @RequestParam(value = "pageSize",required = true) int pageSize,
                                              @RequestParam(value = "sortBy",required = false) String sortBy,
                                              @RequestParam(value = "sortDir",required = false) String sortDir){
        Sort sort;
        if (sortDir != null) {
            sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortBy).ascending() :
                    Sort.by(sortBy).descending();
        } else {
            Sort.Direction sortDirection = Sort.Direction.ASC;
            if (sortBy == null) {
                sortBy = "id";
            }
            sort = Sort.by(sortDirection, sortBy);
        }
        return vehicleService.getListPaginationDTO(PageRequest.of(pageNumber-1,pageSize));
    }

    @GetMapping("/get_by_id_dto/{id}")
    public VehicleDTO getById(@PathVariable("id") long id)  {
        return  vehicleService.getById(id);
    }

}