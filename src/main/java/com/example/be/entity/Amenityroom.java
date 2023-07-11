package com.example.be.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "amenityroom")
@Getter
@Setter
public class Amenityroom extends BaseEntity {
    @NotNull
    private String title;
    @NotNull
    private String name;

    @NotNull
    private int status;
}
