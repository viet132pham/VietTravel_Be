package com.example.be.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

    private String content;

    private String image;

    private int star;

    private long rate;

    private long adminId;

    private long userId;

}
