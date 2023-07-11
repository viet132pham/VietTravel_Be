package com.example.be.controller;

import com.example.be.entity.Room;
import com.example.be.service.BaseService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/room")
public class RoomController extends BaseController<Room> {
    public RoomController(BaseService<Room> baseService) {
        super(baseService);
    }
}