package com.example.be.service;

import com.example.be.entity.Reviews;
import com.example.be.request.ReviewRequest;
import org.springframework.validation.BindingResult;

public interface ReviewService {

    Reviews createRequest(ReviewRequest reviewRequest, BindingResult bindingResult);

    void deleteReview(long id);

    Reviews updateReview(long id, ReviewRequest reviewRequest, BindingResult bindingResult);

}
