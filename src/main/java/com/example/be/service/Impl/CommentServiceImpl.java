package com.example.be.service.Impl;

import com.example.be.dto.BlogDTO;
import com.example.be.dto.CommentDTO;
import com.example.be.dto.UserDTO;
import com.example.be.entity.Blog;
import com.example.be.entity.mapped.Comment;
import com.example.be.repository.BlogRepository;
import com.example.be.repository.CommentRepository;
import com.example.be.repository.UserRepository;
import com.example.be.request.CommentRequest;
import com.example.be.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class CommentServiceImpl implements CommentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CommentRepository commentRepository;

    public Comment createRequest(CommentRequest commentRequest, BindingResult bindingResult) {
        Comment comment = new Comment();
        mapper.map(commentRequest, comment);
        comment.setUser(userRepository.findUserById(commentRequest.getUserId()));
        comment.setBlog(blogRepository.findBlogById(commentRequest.getBlogId()));
        return commentRepository.save(comment);
    }

    public void deleteComment(long id) {
         commentRepository.deleteById(id);
    }

    public Comment updateComment(long id, CommentRequest commentRequest, BindingResult bindingResult) {
        Comment comment = commentRepository.findCommentById(id);
        mapper.map(commentRequest, comment);
        comment.setUser(userRepository.findUserById(commentRequest.getUserId()));
        comment.setBlog(blogRepository.findBlogById(commentRequest.getBlogId()));
        return commentRepository.save(comment);
    }

    public List<CommentDTO> getCommentByBlogId(long id) {
        List<Comment> commentList = commentRepository.findCommentsByBlog(blogRepository.findBlogById(id));
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (int i = 0; i < commentList.size(); i++){
            CommentDTO commentDTO = new CommentDTO();
            UserDTO userDTO = new UserDTO();
            BlogDTO blogDTO = new BlogDTO();
            mapper.map(commentList.get(i), commentDTO);
            mapper.map(commentList.get(i).getUser(), userDTO);
            mapper.map(commentList.get(i).getBlog(), blogDTO);
            // Kiểm tra và khởi tạo blogcategory nếu nó là null
            if (blogDTO.getBlogcategory() == null) {
                blogDTO.setBlogcategory(new ArrayList<>());
            }

            commentList.get(i).getBlog().getBlogcategorie().forEach(blogcategory -> {
                blogDTO.getBlogcategory().add(blogcategory);
            });
            commentDTO.setBlogDTO(blogDTO);
            commentDTO.setUserDTO(userDTO);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    public List<CommentDTO> getAllComment() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (int i = 0; i < commentList.size(); i++){
            CommentDTO commentDTO = new CommentDTO();
            UserDTO userDTO = new UserDTO();
            BlogDTO blogDTO = new BlogDTO();
            mapper.map(commentList.get(i), commentDTO);
            mapper.map(commentList.get(i).getUser(), userDTO);
            mapper.map(commentList.get(i).getBlog(), blogDTO);
            // Kiểm tra và khởi tạo blogcategory nếu nó là null
            if (blogDTO.getBlogcategory() == null) {
                blogDTO.setBlogcategory(new ArrayList<>());
            }

            commentList.get(i).getBlog().getBlogcategorie().forEach(blogcategory -> {
                blogDTO.getBlogcategory().add(blogcategory);
            });
            commentDTO.setBlogDTO(blogDTO);
            commentDTO.setUserDTO(userDTO);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
