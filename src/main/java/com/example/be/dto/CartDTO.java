package com.example.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class CartDTO {
    private String id;
    private String status;
    private String priceTotal;
    private String paymentMethod;
    private UserDTO userDTO;
}
