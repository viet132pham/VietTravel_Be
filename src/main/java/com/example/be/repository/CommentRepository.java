package com.example.be.repository;

import com.example.be.entity.Blog;
import com.example.be.entity.mapped.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    public Comment findCommentById(long id);

    public List<Comment> findCommentsByBlog(Blog blog);
}
