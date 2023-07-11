package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "location")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Location extends BaseEntity{
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private int hot;
    @NotNull
    private int status;
    @NotNull
    private String image;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private User admin;
}
