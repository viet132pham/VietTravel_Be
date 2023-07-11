package com.example.be.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private String status;
    private String priceTotal;
    private String paymentMethod;
    private String email;
    private String phone;
    private String fullName;
    private long userId;
}
