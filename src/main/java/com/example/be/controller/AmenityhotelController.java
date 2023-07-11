package com.example.be.controller;

import com.example.be.entity.Amenityhotel;
import com.example.be.service.BaseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/amenityhotel")
public class AmenityhotelController extends BaseController<Amenityhotel> {
    public AmenityhotelController(BaseService<Amenityhotel> baseService) {
        super(baseService);
    }

}