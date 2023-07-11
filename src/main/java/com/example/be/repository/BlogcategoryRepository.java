package com.example.be.repository;

import com.example.be.entity.Blogcategory;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogcategoryRepository extends BaseRepository<Blogcategory, Long> {
    public Blogcategory findBlogcategoryByName(String name);
}
