package com.example.be.service.Impl;

import com.example.be.dto.*;
import com.example.be.entity.Hotel;
import com.example.be.entity.Location;
import com.example.be.entity.Reviews;
import com.example.be.entity.Tour;
import com.example.be.repository.BaseRepository;
import com.example.be.repository.CartitemRepository;
import com.example.be.repository.HotelRepository;
import com.example.be.repository.LocationRepository;
import com.example.be.service.HotelService;
import com.example.be.util.Utils;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Log4j2
public class HotelServiceImpl extends BaseServiceImpl<Hotel> implements HotelService {
    public HotelServiceImpl(BaseRepository<Hotel, Long> baseRepo, Utils utils) {
        super(baseRepo, utils);
    }

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CartitemRepository cartitemRepository;
    @Autowired
    private ModelMapper mapper;

    public List<HotelDTO> findSaleHotel() {
        List<Hotel> result = hotelRepository.findSaleHotel();
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++){
            HotelDTO hotelDTO = new HotelDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Hotel hotel = result.get(i);
            mapper.map(hotel, hotelDTO);

            mapper.map(hotel.getLocation(), locationDTO);
            hotelDTO.setLocationDTO(locationDTO);
            if (hotelDTO.getAmenityhotel() == null) {
                hotelDTO.setAmenityhotel(new ArrayList<>());
            }

            hotel.getAmenityhotels().forEach(amenitytour -> {
                hotelDTO.getAmenityhotel().add(amenitytour);
            });
            Set<Reviews> reviewsSet = hotel.getReviews();
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

            hotelDTO.setReviewsDTOS(reviewsDTOList);

            hotelDTOList.add(hotelDTO);
        }

        return hotelDTOList;
    }

    public List<HotelDTO> findHotelTrending() {
        List<Integer> cartitems = cartitemRepository.findCategoryIdInCartitem("hotel");
        System.out.println(cartitems);
        List<Hotel> result = new ArrayList<>();

        cartitems.forEach(cartitem -> {
            result.add(hotelRepository.findHotelById(cartitem));
        });
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++){
            HotelDTO hotelDTO = new HotelDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Hotel hotel = result.get(i);
            mapper.map(hotel, hotelDTO);

            mapper.map(hotel.getLocation(), locationDTO);
            hotelDTO.setLocationDTO(locationDTO);
            if (hotelDTO.getAmenityhotel() == null) {
                hotelDTO.setAmenityhotel(new ArrayList<>());
            }

            hotel.getAmenityhotels().forEach(amenitytour -> {
                hotelDTO.getAmenityhotel().add(amenitytour);
            });
            Set<Reviews> reviewsSet = hotel.getReviews();
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

            hotelDTO.setReviewsDTOS(reviewsDTOList);

            hotelDTOList.add(hotelDTO);
        }

        return hotelDTOList;
    }
    @SneakyThrows
    public List<HotelDTO> searchHotels(String location) {
        Location location1 = locationRepository.findLocationByName(location);
        List<Hotel> result = hotelRepository.findHotelByLocation(location1);
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++){
            HotelDTO hotelDTO = new HotelDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Hotel hotel = result.get(i);
            mapper.map(hotel, hotelDTO);

            mapper.map(hotel.getLocation(), locationDTO);
            hotelDTO.setLocationDTO(locationDTO);
            if (hotelDTO.getAmenityhotel() == null) {
                hotelDTO.setAmenityhotel(new ArrayList<>());
            }

            hotel.getAmenityhotels().forEach(amenitytour -> {
                hotelDTO.getAmenityhotel().add(amenitytour);
            });
            Set<Reviews> reviewsSet = hotel.getReviews();
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

            hotelDTO.setReviewsDTOS(reviewsDTOList);

            hotelDTOList.add(hotelDTO);
        }

        return hotelDTOList;
    }

    public HotelDTO findHotelByName(String name) {
        Hotel hotel = hotelRepository.findHotelByName(name);
        HotelDTO hotelDTO = new HotelDTO();
        LocationDTO locationDTO = new LocationDTO();
        ReviewsDTO reviewsDTO = new ReviewsDTO();
        mapper.map(hotel, hotelDTO);

        mapper.map(hotel.getLocation(), locationDTO);
        hotelDTO.setLocationDTO(locationDTO);
        if (hotelDTO.getAmenityhotel() == null) {
            hotelDTO.setAmenityhotel(new ArrayList<>());
        }

        hotel.getAmenityhotels().forEach(amenitytour -> {
            hotelDTO.getAmenityhotel().add(amenitytour);
        });
        Set<Reviews> reviewsSet = hotel.getReviews();
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

        hotelDTO.setReviewsDTOS(reviewsDTOList);

        return hotelDTO;
    }
    @SneakyThrows
    public Page<HotelDTO> filterHotels(Pageable pageable, String name, String checkIn, String checkOut, String priceStart, String priceEnd, String sale) {
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

        Page<Hotel> hotels = hotelRepository.filterHotels(name, checkInTimestamp, checkOutTimestamp, priceStartString, priceEndString, saleWrap, pageable);
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        for (int i = 0; i < hotels.getContent().size(); i++){
            HotelDTO hotelDTO = new HotelDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Hotel hotel = hotels.getContent().get(i);
            mapper.map(hotel, hotelDTO);

            mapper.map(hotel.getLocation(), locationDTO);
            hotelDTO.setLocationDTO(locationDTO);
            if (hotelDTO.getAmenityhotel() == null) {
                hotelDTO.setAmenityhotel(new ArrayList<>());
            }

            hotel.getAmenityhotels().forEach(amenitytour -> {
                hotelDTO.getAmenityhotel().add(amenitytour);
            });
            Set<Reviews> reviewsSet = hotel.getReviews();
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

            hotelDTO.setReviewsDTOS(reviewsDTOList);

            hotelDTOList.add(hotelDTO);
        }

        Page<HotelDTO> hotelDTOPage = new PageImpl<>(hotelDTOList, hotels.getPageable(), hotels.getTotalElements());

        return hotelDTOPage;
    }

    public Page<HotelDTO> getListPaginationDTO(Pageable pageable) {
        Page<Hotel> hotels = hotelRepository.findAll(pageable);
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        for (int i = 0; i < hotels.getContent().size(); i++){
            HotelDTO hotelDTO = new HotelDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Hotel hotel = hotels.getContent().get(i);
            mapper.map(hotel, hotelDTO);

            mapper.map(hotel.getLocation(), locationDTO);
            hotelDTO.setLocationDTO(locationDTO);
            if (hotelDTO.getAmenityhotel() == null) {
                hotelDTO.setAmenityhotel(new ArrayList<>());
            }

            hotel.getAmenityhotels().forEach(amenitytour -> {
                hotelDTO.getAmenityhotel().add(amenitytour);
            });
            Set<Reviews> reviewsSet = hotel.getReviews();
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

            hotelDTO.setReviewsDTOS(reviewsDTOList);

            hotelDTOList.add(hotelDTO);
        }

        Page<HotelDTO> hotelDTOPage = new PageImpl<>(hotelDTOList, hotels.getPageable(), hotels.getTotalElements());
        return hotelDTOPage;
    }

    public Page<HotelDTO> getSortedAndPaginateDTO(Pageable pageable) {
        Page<Hotel> hotels = hotelRepository.findAll(pageable);
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        for (int i = 0; i < hotels.getContent().size(); i++){
            HotelDTO hotelDTO = new HotelDTO();
            LocationDTO locationDTO = new LocationDTO();
            ReviewsDTO reviewsDTO = new ReviewsDTO();
            Hotel hotel = hotels.getContent().get(i);
            mapper.map(hotel, hotelDTO);

            mapper.map(hotel.getLocation(), locationDTO);
            hotelDTO.setLocationDTO(locationDTO);
            if (hotelDTO.getAmenityhotel() == null) {
                hotelDTO.setAmenityhotel(new ArrayList<>());
            }

            hotel.getAmenityhotels().forEach(amenitytour -> {
                hotelDTO.getAmenityhotel().add(amenitytour);
            });
            Set<Reviews> reviewsSet = hotel.getReviews();
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

            hotelDTO.setReviewsDTOS(reviewsDTOList);

            hotelDTOList.add(hotelDTO);
        }

        Page<HotelDTO> hotelDTOPage = new PageImpl<>(hotelDTOList, hotels.getPageable(), hotels.getTotalElements());
        return hotelDTOPage;
    }

    @Override
    public HotelDTO getById(long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
        HotelDTO hotelDTO = new HotelDTO();
        LocationDTO locationDTO = new LocationDTO();
        ReviewsDTO reviewsDTO = new ReviewsDTO();
        mapper.map(hotel, hotelDTO);

        mapper.map(hotel.getLocation(), locationDTO);
        hotelDTO.setLocationDTO(locationDTO);
        if (hotelDTO.getAmenityhotel() == null) {
            hotelDTO.setAmenityhotel(new ArrayList<>());
        }

        hotel.getAmenityhotels().forEach(amenitytour -> {
            hotelDTO.getAmenityhotel().add(amenitytour);
        });

//        if (hotelDTO.getLandmarks() == null) {
//            hotelDTO.setLandmarks(new ArrayList<>());
//        }
//
//        hotel.getLandmarks().forEach(landmark -> {
//            hotelDTO.getLandmarks().add(landmark);
//        });
        Set<Reviews> reviewsSet = hotel.getReviews();
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

        hotelDTO.setReviewsDTOS(reviewsDTOList);

        return hotelDTO;
    }

}
