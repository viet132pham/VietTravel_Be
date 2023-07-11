package com.example.be.dto;

import com.example.be.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class HotelDTO {
    private int id;
    private String code;
    private String name;
    private int numberGuest;
    private int sale;
    private int star;
    private int status;
    private String image;
    private int price;
    private String description;
    private String timeStart;
    private String timeEnd;
    private LocationDTO locationDTO;
    private ArrayList<Amenityhotel> amenityhotel;
    private List<ReviewsDTO> reviewsDTOS;
    private ArrayList<Landmarks> landmarks;
    private ArrayList<Room> rooms;
    private ArrayList<Rules> rules;
    private Date createdAt;
    private Date updatedAt;
    private String address;
}
