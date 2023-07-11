package com.example.be.repository;

import com.example.be.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Long>{

}
