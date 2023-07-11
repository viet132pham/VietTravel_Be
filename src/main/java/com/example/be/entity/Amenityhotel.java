package com.example.be.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "amenityhotel")
@Getter
@Setter
public class Amenityhotel extends BaseEntity {
    @NotNull
    private String title;
    @NotNull
    private String name;

    @NotNull
    private int status;
}
