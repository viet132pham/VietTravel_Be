package com.example.be.controller;

import com.example.be.entity.Amenitytour;
import com.example.be.service.BaseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/amenitytour")
public class AmenitytourController extends BaseController<Amenitytour> {
    public AmenitytourController(BaseService<Amenitytour> baseService) {
        super(baseService);
    }

}