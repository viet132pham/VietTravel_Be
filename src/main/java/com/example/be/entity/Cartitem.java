package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cartitem extends BaseEntity{
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @NotNull
    private String categoryName;

    @NotNull
    private int categoryId;

    @NotNull
    private String name;

    @NotNull
    private int price;

    @NotNull
    private int quantity;

    @NotNull
    private String image;
}
