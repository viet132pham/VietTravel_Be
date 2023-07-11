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
@Table(name = "vehicle")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle extends BaseEntity{
    @NotNull
    private String code;
    @NotNull
    private String name;
    @NotNull
    private int numberGuest;
    @NotNull
    private int sale;
    @NotNull
    private int status;
    @NotNull
    private String image;
    @NotNull
    private int price;
    @NotNull
    private String description;
    @NotNull
    private Date timeStart;
    @NotNull
    private Date timeEnd;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "vehicle_review", joinColumns = {@JoinColumn(name = "vehicle_id")}, inverseJoinColumns = {@JoinColumn(name = "review_id")})
    private Set<Reviews> reviews = new HashSet<>();
}
