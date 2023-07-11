package com.example.be.controller;

import com.example.be.entity.Landmarks;
import com.example.be.service.BaseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/landmark")
public class LandmarksController extends BaseController<Landmarks> {
    public LandmarksController(BaseService<Landmarks> baseService) {
        super(baseService);
    }

}