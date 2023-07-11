package com.example.be.service.Impl;

import com.example.be.entity.Amenitytour;
import com.example.be.repository.BaseRepository;
import com.example.be.service.AmenitytourService;
import com.example.be.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AmenitytourServiceImpl extends BaseServiceImpl<Amenitytour> implements AmenitytourService {
    public AmenitytourServiceImpl(BaseRepository<Amenitytour, Long> baseRepo, Utils utils) {
        super(baseRepo, utils);
    }

}
