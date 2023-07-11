package com.example.be.repository;

import com.example.be.entity.Landmarks;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandmarksRepository extends BaseRepository<Landmarks, Long> {

}
