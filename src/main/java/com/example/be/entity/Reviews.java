package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reviews extends BaseEntity {
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String content;

    @NotNull
    private String image;

    @NotNull
    private int star;

    @NotNull
    private int rate;
}
