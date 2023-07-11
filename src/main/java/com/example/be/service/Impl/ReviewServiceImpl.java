package com.example.be.service.Impl;

import com.example.be.entity.Reviews;
import com.example.be.repository.ReviewRepository;
import com.example.be.repository.UserRepository;
import com.example.be.request.ReviewRequest;
import com.example.be.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@Log4j2
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;
    public Reviews createRequest(ReviewRequest reviewRequest, BindingResult bindingResult) {
        Reviews reviews = new Reviews();
        mapper.map(reviewRequest, reviews);
        reviews.setUser(userRepository.findUserById(reviewRequest.getUserId()));
        reviews.setAdmin(userRepository.findUserById(reviewRequest.getAdminId()));
        return reviewRepository.save(reviews);
    }

    public void deleteReview(long id) {
        reviewRepository.deleteById(id);
    }

    public Reviews updateReview(long id, ReviewRequest reviewRequest, BindingResult bindingResult) {
        Reviews reviews = reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("id not found: " + id)));
        mapper.map(reviewRequest, reviews);
        reviews.setUser(userRepository.findUserById(reviewRequest.getUserId()));
        reviews.setAdmin(userRepository.findUserById(reviewRequest.getAdminId()));
        return reviewRepository.save(reviews);
    }

}
