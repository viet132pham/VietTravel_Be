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
@Table(name = "tour")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tour extends BaseEntity{
    @NotNull
    private String code;
    @NotNull
    private String name;

    // ví dụ tour này dành cho 3, 4 , 2, hay 1 người
    @NotNull
    private int numberGuest;
    @NotNull
    private int sale;
    @NotNull
    private int status;
    @NotNull
    private String image;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "tour_include", joinColumns = {@JoinColumn(name = "tour_id")}, inverseJoinColumns = {@JoinColumn(name = "include_id")})
    private Set<Included> includeds = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "tour_exclude", joinColumns = {@JoinColumn(name = "tour_id")}, inverseJoinColumns = {@JoinColumn(name = "exclude_id")})
    private Set<Excluded> excludeds = new HashSet<>();

    @NotNull
    private int price;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "tour_itinerary", joinColumns = {@JoinColumn(name = "tour_id")}, inverseJoinColumns = {@JoinColumn(name = "itinerary_id")})
    private Set<Itinerary> itineraries = new HashSet<>();

    @NotNull
    private String description;
    @NotNull
    private String timeStart;
    @NotNull
    private String time;
    @NotNull
    private String vehicle;
    @NotNull
    private String hotel;
    @NotNull
    private String locationStart;
    @NotNull
    private String locationEnd;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "tour_review", joinColumns = {@JoinColumn(name = "tour_id")}, inverseJoinColumns = {@JoinColumn(name = "review_id")})
    private Set<Reviews> reviews = new HashSet<>();

    private Date createdAt;

    private Date updatedAt;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "tour_rule", joinColumns = {@JoinColumn(name = "tour_id")}, inverseJoinColumns = {@JoinColumn(name = "rule_id")})
    private Set<Rules> rules = new HashSet<>();
}
