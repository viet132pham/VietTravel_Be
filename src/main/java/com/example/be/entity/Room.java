package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseEntity{
    @NotNull
    private String name;
    @NotNull
    private String hotelName;
    @NotNull
    private String bed;
    @NotNull
    private String numberGuest;
    @NotNull
    private String roomBlank;
    @NotNull
    private int sale;
    @NotNull
    private int status;
    @NotNull
    private String image;
    @NotNull
    private int price;
    @NotNull
    private Date timeStart; // ngay nhan phong
    @NotNull
    private Date timeEnd; // ngay tra phong

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "room_amenityroom", joinColumns = {@JoinColumn(name = "room_id")}, inverseJoinColumns = {@JoinColumn(name = "amenityroom_id")})
    private Set<Amenityroom> amenityrooms = new HashSet<>();

}
