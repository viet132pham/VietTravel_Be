package com.example.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class ReviewsDTO {
    private String content;
    private String image;
    private int star;
    private int rate;
    private UserDTO user;
}
