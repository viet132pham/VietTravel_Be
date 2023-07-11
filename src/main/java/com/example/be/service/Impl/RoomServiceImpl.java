package com.example.be.service.Impl;

import com.example.be.entity.Room;
import com.example.be.repository.BaseRepository;
import com.example.be.service.RoomService;
import com.example.be.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RoomServiceImpl extends BaseServiceImpl<Room> implements RoomService {
    public RoomServiceImpl(BaseRepository<Room, Long> baseRepo, Utils utils) {
        super(baseRepo, utils);
    }

}
