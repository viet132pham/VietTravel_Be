package com.example.be.controller;

import com.example.be.entity.Location;
import com.example.be.repository.LocationRepository;
import com.example.be.response.TopDestination;
import com.example.be.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/location")
public class LocationController extends BaseController<Location> {
    public LocationController(BaseService<Location> baseService) {
        super(baseService);
    }

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/find/topdestination")
    public List<TopDestination> findTopDestination() {
        return locationRepository.findTopDestinations();
    }

}