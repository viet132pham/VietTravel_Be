package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity{
    @NotNull
    private String status;

    private String priceTotal;

    private String paymentMethod;

    private String email;

    private String phone;

    private String fullName;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
