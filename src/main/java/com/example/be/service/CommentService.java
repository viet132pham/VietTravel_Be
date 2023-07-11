package com.example.be.service;

import com.example.be.dto.CommentDTO;
import com.example.be.entity.mapped.Comment;
import com.example.be.request.CommentRequest;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CommentService {

    Comment createRequest(CommentRequest commentRequest, BindingResult bindingResult);

    void deleteComment(long id);

    Comment updateComment(long id, CommentRequest commentRequest, BindingResult bindingResult);

    List<CommentDTO> getCommentByBlogId(long id);

    List<CommentDTO> getAllComment();
}
