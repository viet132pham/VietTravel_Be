package com.example.be.service.Impl;

import com.example.be.entity.Amenityhotel;
import com.example.be.repository.BaseRepository;
import com.example.be.service.AmenityhotelService;
import com.example.be.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AmenityhotelServiceImpl extends BaseServiceImpl<Amenityhotel> implements AmenityhotelService {
    public AmenityhotelServiceImpl(BaseRepository<Amenityhotel, Long> baseRepo, Utils utils) {
        super(baseRepo, utils);
    }

}
