package com.example.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class CartitemDTO {
    private int id;
    private CartDTO cartDTO;
    private String categoryName;
    private int categoryId;
    private String name;
    private int price;
    private int quantity;
    private String image;
    private int sale;
}
