package com.example.be.service.Impl;

import com.example.be.dto.*;
import com.example.be.entity.Hotel;
import com.example.be.entity.Location;
import com.example.be.entity.Reviews;
import com.example.be.entity.Vehicle;
import com.example.be.repository.BaseRepository;
import com.example.be.repository.CartitemRepository;
import com.example.be.repository.LocationRepository;
import com.example.be.repository.VehicleRepository;
import com.example.be.service.VehicleService;
import com.example.be.util.Utils;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Log4j2
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle> implements VehicleService {
    public VehicleServiceImpl(BaseRepository<Vehicle, Long> baseRepo, Utils utils) {
        super(baseRepo, utils);
    }
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CartitemRepository cartitemRepository;

    @Autowired
    private ModelMapper mapper;

    public  List<VehicleDTO> findSaleVehicle() {
        List<Vehicle> result = vehicleRepository.findSaleVehicle();
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++){
            VehicleDTO vehicleDTO = new VehicleDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Vehicle vehicle = result.get(i);
            mapper.map(vehicle, vehicleDTO);

            mapper.map(vehicle.getLocation(), locationDTO);
            vehicleDTO.setLocationDTO(locationDTO);

            Set<Reviews> reviewsSet = vehicle.getReviews();
            List<Reviews> reviewsList = new ArrayList<>(reviewsSet);

            List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

            for (int j = 0; j < reviewsList.size(); j++){
                UserDTO userDTO = new UserDTO();
                ReviewsDTO reviewsDTO1 = new ReviewsDTO();
                mapper.map(reviewsList.get(j), reviewsDTO1);
                mapper.map(reviewsList.get(j).getUser(), userDTO);
                reviewsDTO1.setUser(userDTO);
                reviewsDTOList.add(reviewsDTO1);
            }

            vehicleDTO.setReviewsDTOS(reviewsDTOList);

            vehicleDTOList.add(vehicleDTO);
        }
        return  vehicleDTOList;
    }
    public List<VehicleDTO> findVehicleTrending() {
        List<Integer> cartitems = cartitemRepository.findCategoryIdInCartitem("vehicle");
        System.out.println(cartitems);
        List<Vehicle> result = new ArrayList<>();

        cartitems.forEach(cartitem -> {
            result.add(vehicleRepository.findVehicleById(cartitem));
        });
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++){
            VehicleDTO vehicleDTO = new VehicleDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Vehicle vehicle = result.get(i);
            mapper.map(vehicle, vehicleDTO);

            mapper.map(vehicle.getLocation(), locationDTO);
            vehicleDTO.setLocationDTO(locationDTO);

            Set<Reviews> reviewsSet = vehicle.getReviews();
            List<Reviews> reviewsList = new ArrayList<>(reviewsSet);

            List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

            for (int j = 0; j < reviewsList.size(); j++){
                UserDTO userDTO = new UserDTO();
                ReviewsDTO reviewsDTO1 = new ReviewsDTO();
                mapper.map(reviewsList.get(j), reviewsDTO1);
                mapper.map(reviewsList.get(j).getUser(), userDTO);
                reviewsDTO1.setUser(userDTO);
                reviewsDTOList.add(reviewsDTO1);
            }

            vehicleDTO.setReviewsDTOS(reviewsDTOList);

            vehicleDTOList.add(vehicleDTO);
        }
        return  vehicleDTOList;
    }

    public List<VehicleDTO> findVehicleByLocation(String location) {
        Location location1 = locationRepository.findLocationByName(location);
        List<Vehicle> result = vehicleRepository.findVehicleByLocation(location1);
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++){
            VehicleDTO vehicleDTO = new VehicleDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Vehicle vehicle = result.get(i);
            mapper.map(vehicle, vehicleDTO);

            mapper.map(vehicle.getLocation(), locationDTO);
            vehicleDTO.setLocationDTO(locationDTO);

            Set<Reviews> reviewsSet = vehicle.getReviews();
            List<Reviews> reviewsList = new ArrayList<>(reviewsSet);

            List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

            for (int j = 0; j < reviewsList.size(); j++){
                UserDTO userDTO = new UserDTO();
                ReviewsDTO reviewsDTO1 = new ReviewsDTO();
                mapper.map(reviewsList.get(j), reviewsDTO1);
                mapper.map(reviewsList.get(j).getUser(), userDTO);
                reviewsDTO1.setUser(userDTO);
                reviewsDTOList.add(reviewsDTO1);
            }

            vehicleDTO.setReviewsDTOS(reviewsDTOList);

            vehicleDTOList.add(vehicleDTO);
        }
        return  vehicleDTOList;
    }

    public VehicleDTO findVehicleByName(String name) {
        Vehicle vehicle = vehicleRepository.findVehicleByName(name);
        VehicleDTO vehicleDTO = new VehicleDTO();
        LocationDTO locationDTO = new LocationDTO();
        ReviewsDTO reviewsDTO = new ReviewsDTO();
        mapper.map(vehicle, vehicleDTO);

        mapper.map(vehicle.getLocation(), locationDTO);
        vehicleDTO.setLocationDTO(locationDTO);

        Set<Reviews> reviewsSet = vehicle.getReviews();
        List<Reviews> reviewsList = new ArrayList<>(reviewsSet);

        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

        for (int j = 0; j < reviewsList.size(); j++){
            UserDTO userDTO = new UserDTO();
            ReviewsDTO reviewsDTO1 = new ReviewsDTO();
            mapper.map(reviewsList.get(j), reviewsDTO1);
            mapper.map(reviewsList.get(j).getUser(), userDTO);
            reviewsDTO1.setUser(userDTO);
            reviewsDTOList.add(reviewsDTO1);
        }

        vehicleDTO.setReviewsDTOS(reviewsDTOList);

        return vehicleDTO;
    }

    @SneakyThrows
    public Page<VehicleDTO> filterVehicles(Pageable pageable, String name, String checkIn, String checkOut, String priceStart, String priceEnd, String sale) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Timestamp checkInTimestamp = null;
        if (checkIn != null && !checkIn.equals("undefined") && !checkIn.equals("null")) {
            Date checkInConvert = formatter.parse(checkIn);
            checkInTimestamp = new Timestamp(checkInConvert.getTime());
        }

        Timestamp checkOutTimestamp = null;
        if (checkOut != null && !checkOut.equals("undefined") && !checkOut.equals("null")) {
            Date checkOutConvert = formatter.parse(checkOut);
            checkOutTimestamp = new Timestamp(checkOutConvert.getTime());
        }

        Integer priceStartString = null;
        if (priceStart != null && !priceStart.equals("undefined") && !priceStart.equals("null")) {
            priceStartString = Integer.parseInt(priceStart);
        }

        Integer priceEndString = null;
        if (priceEnd != null && !priceEnd.equals("undefined") && !priceEnd.equals("null")) {
            priceEndString = Integer.parseInt(priceEnd);
        }

        Integer saleWrap = null;
        if (sale != null && !sale.equals("undefined") && !sale.equals("null")) {
            saleWrap = Integer.parseInt(sale);
        }

        Page<Vehicle> vehicles = vehicleRepository.filterVehicles(name, checkInTimestamp, checkOutTimestamp, priceStartString, priceEndString, saleWrap, pageable);
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for (int i = 0; i < vehicles.getContent().size(); i++){
            VehicleDTO vehicleDTO = new VehicleDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Vehicle vehicle = vehicles.getContent().get(i);
            mapper.map(vehicle, vehicleDTO);

            mapper.map(vehicle.getLocation(), locationDTO);
            vehicleDTO.setLocationDTO(locationDTO);

            Set<Reviews> reviewsSet = vehicle.getReviews();
            List<Reviews> reviewsList = new ArrayList<>(reviewsSet);

            List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

            for (int j = 0; j < reviewsList.size(); j++){
                UserDTO userDTO = new UserDTO();
                ReviewsDTO reviewsDTO1 = new ReviewsDTO();
                mapper.map(reviewsList.get(j), reviewsDTO1);
                mapper.map(reviewsList.get(j).getUser(), userDTO);
                reviewsDTO1.setUser(userDTO);
                reviewsDTOList.add(reviewsDTO1);
            }

            vehicleDTO.setReviewsDTOS(reviewsDTOList);

            vehicleDTOList.add(vehicleDTO);
        }

        Page<VehicleDTO> vehicleDTOPage = new PageImpl<>(vehicleDTOList, vehicles.getPageable(), vehicles.getTotalElements());
        return vehicleDTOPage;
    }

    public Page<VehicleDTO> getListPaginationDTO(Pageable pageable) {
        Page<Vehicle> vehicles = vehicleRepository.findAll(pageable);
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for (int i = 0; i < vehicles.getContent().size(); i++){
            VehicleDTO vehicleDTO = new VehicleDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Vehicle vehicle = vehicles.getContent().get(i);
            mapper.map(vehicle, vehicleDTO);

            mapper.map(vehicle.getLocation(), locationDTO);
            vehicleDTO.setLocationDTO(locationDTO);

            Set<Reviews> reviewsSet = vehicle.getReviews();
            List<Reviews> reviewsList = new ArrayList<>(reviewsSet);

            List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

            for (int j = 0; j < reviewsList.size(); j++){
                UserDTO userDTO = new UserDTO();
                ReviewsDTO reviewsDTO1 = new ReviewsDTO();
                mapper.map(reviewsList.get(j), reviewsDTO1);
                mapper.map(reviewsList.get(j).getUser(), userDTO);
                reviewsDTO1.setUser(userDTO);
                reviewsDTOList.add(reviewsDTO1);
            }

            vehicleDTO.setReviewsDTOS(reviewsDTOList);

            vehicleDTOList.add(vehicleDTO);
        }

        Page<VehicleDTO> vehicleDTOPage = new PageImpl<>(vehicleDTOList, vehicles.getPageable(), vehicles.getTotalElements());
        return vehicleDTOPage;
    }

    public Page<VehicleDTO> getSortedAndPaginateDTO(Pageable pageable) {
        Page<Vehicle> vehicles = vehicleRepository.findAll(pageable);
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for (int i = 0; i < vehicles.getContent().size(); i++){
            VehicleDTO vehicleDTO = new VehicleDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Vehicle vehicle = vehicles.getContent().get(i);
            mapper.map(vehicle, vehicleDTO);

            mapper.map(vehicle.getLocation(), locationDTO);
            vehicleDTO.setLocationDTO(locationDTO);

            Set<Reviews> reviewsSet = vehicle.getReviews();
            List<Reviews> reviewsList = new ArrayList<>(reviewsSet);

            List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

            for (int j = 0; j < reviewsList.size(); j++){
                UserDTO userDTO = new UserDTO();
                ReviewsDTO reviewsDTO1 = new ReviewsDTO();
                mapper.map(reviewsList.get(j), reviewsDTO1);
                mapper.map(reviewsList.get(j).getUser(), userDTO);
                reviewsDTO1.setUser(userDTO);
                reviewsDTOList.add(reviewsDTO1);
            }

            vehicleDTO.setReviewsDTOS(reviewsDTOList);

            vehicleDTOList.add(vehicleDTO);
        }

        Page<VehicleDTO> vehicleDTOPage = new PageImpl<>(vehicleDTOList, vehicles.getPageable(), vehicles.getTotalElements());
        return vehicleDTOPage;
    }

    @Override
    public VehicleDTO getById(long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
        VehicleDTO vehicleDTO = new VehicleDTO();
        LocationDTO locationDTO = new LocationDTO();
        ReviewsDTO reviewsDTO = new ReviewsDTO();
        mapper.map(vehicle, vehicleDTO);

        mapper.map(vehicle.getLocation(), locationDTO);
        vehicleDTO.setLocationDTO(locationDTO);

        Set<Reviews> reviewsSet = vehicle.getReviews();
        List<Reviews> reviewsList = new ArrayList<>(reviewsSet);

        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

        for (int j = 0; j < reviewsList.size(); j++){
            UserDTO userDTO = new UserDTO();
            ReviewsDTO reviewsDTO1 = new ReviewsDTO();
            mapper.map(reviewsList.get(j), reviewsDTO1);
            mapper.map(reviewsList.get(j).getUser(), userDTO);
            reviewsDTO1.setUser(userDTO);
            reviewsDTOList.add(reviewsDTO1);
        }

        vehicleDTO.setReviewsDTOS(reviewsDTOList);

        return vehicleDTO;
    }

}
