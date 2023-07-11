package com.example.be.controller;

import com.example.be.entity.Reviews;
import com.example.be.request.ReviewRequest;
import com.example.be.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/post")
    public Reviews postRequest(@RequestBody @Valid ReviewRequest reviewRequest, BindingResult bindingResult) {
        return reviewService.createRequest(reviewRequest, bindingResult);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReview(@PathVariable(value = "id") long id){
        reviewService.deleteReview(id);
    }

    @PutMapping("/put/{id}")
    public Reviews updateReview(@PathVariable(value = "id") long id, @RequestBody @Valid ReviewRequest reviewRequest, BindingResult bindingResult) {
        return reviewService.updateReview(id, reviewRequest, bindingResult);
    }

}