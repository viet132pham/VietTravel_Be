package com.example.be.service.Impl;

import com.example.be.entity.Landmarks;
import com.example.be.repository.BaseRepository;
import com.example.be.service.LandmarksService;
import com.example.be.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class LandmarksServiceImpl extends BaseServiceImpl<Landmarks> implements LandmarksService {
    public LandmarksServiceImpl(BaseRepository<Landmarks, Long> baseRepo, Utils utils) {
        super(baseRepo, utils);
    }

}
