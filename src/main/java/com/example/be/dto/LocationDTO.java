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
public class LocationDTO {
    private String name;
    private String description;
    private int hot;
    private int status;
    private String image;
}
