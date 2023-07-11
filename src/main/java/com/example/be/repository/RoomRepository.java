package com.example.be.repository;

import com.example.be.entity.Room;

import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends BaseRepository<Room, Long>{
    Room findRoomById(long id);
}
