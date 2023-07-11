package com.example.be.repository;

import com.example.be.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends BaseRepository<Blog, Long> {
    public Blog findBlogById(long id);
    @Query(value = "SELECT * " +
            "FROM blog b " +
            "INNER JOIN blog_blogcategory bc ON bc.blog_id = b.id " +
            "WHERE bc.blogcategory_id = :id " +
            "GROUP BY b.id ", nativeQuery = true)
    Page<Blog> findBlogsByCategoryId(Pageable pageable, Long id);
}
