package com.example.be.controller;

import com.example.be.dto.CommentDTO;
import com.example.be.entity.mapped.Comment;
import com.example.be.request.CommentRequest;
import com.example.be.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post")
    public Comment postRequest(@RequestBody @Valid CommentRequest commentRequest, BindingResult bindingResult) {
        return commentService.createRequest(commentRequest, bindingResult);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable(value = "id") long id){
        commentService.deleteComment(id);
    }

    @PutMapping("/put/{id}")
    public Comment updateComment(@PathVariable(value = "id") long id, @RequestBody @Valid CommentRequest commentRequest, BindingResult bindingResult) {
        return commentService.updateComment(id, commentRequest, bindingResult);
    }

    // get comment theo blog id, gen ra những comment có trong blog này
    @GetMapping("/get/{id}")
    public List<CommentDTO> getCommentByBlogId(@PathVariable(value = "id") long id){
        return commentService.getCommentByBlogId(id);
    }

    // get all comment, quản lý trong admin site
    @GetMapping("/get")
    public List<CommentDTO> getAllComment(){
        return commentService.getAllComment();
    }
}