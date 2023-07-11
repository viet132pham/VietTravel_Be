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
@Table(name = "hotel")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel extends BaseEntity{
    @NotNull
    private String code;
    @NotNull
    private String name;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "hotel_room", joinColumns = {@JoinColumn(name = "hotel_id")}, inverseJoinColumns = {@JoinColumn(name = "room_id")})
    private Set<Room> rooms = new HashSet<>();
    @NotNull
    private int status;
    @NotNull
    private int star;
    @NotNull
    private String image;
    @NotNull
    private int price;
    @NotNull
    private int sale;
    @NotNull
    private String description;
    @NotNull
    private String address;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "hotel_amenity", joinColumns = {@JoinColumn(name = "hotel_id")}, inverseJoinColumns = {@JoinColumn(name = "amenityhotel_id")})
    private Set<Amenityhotel> amenityhotels = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "hotel_landmark", joinColumns = {@JoinColumn(name = "hotel_id")}, inverseJoinColumns = {@JoinColumn(name = "landmark_id")})
    private Set<Landmarks> landmarks = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "hotel_review", joinColumns = {@JoinColumn(name = "hotel_id")}, inverseJoinColumns = {@JoinColumn(name = "review_id")})
    private Set<Reviews> reviews = new HashSet<>();

    private Date createdAt;

    private Date updatedAt;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "hotel_rule", joinColumns = {@JoinColumn(name = "hotel_id")}, inverseJoinColumns = {@JoinColumn(name = "rule_id")})
    private Set<Rules> rules = new HashSet<>();
}
