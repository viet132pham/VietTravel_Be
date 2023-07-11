package com.example.be.service.Impl;

import com.example.be.entity.Location;
import com.example.be.repository.BaseRepository;
import com.example.be.service.LocationService;
import com.example.be.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class LocationServiceImpl extends BaseServiceImpl<Location> implements LocationService {
    public LocationServiceImpl(BaseRepository<Location, Long> baseRepo, Utils utils) {
        super(baseRepo, utils);
    }

}
