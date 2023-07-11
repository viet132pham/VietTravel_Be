package com.example.be.service.Impl;

import com.example.be.dto.*;
import com.example.be.entity.Blog;
import com.example.be.entity.Reviews;
import com.example.be.repository.BaseRepository;
import com.example.be.repository.BlogRepository;
import com.example.be.repository.BlogcategoryRepository;
import com.example.be.repository.UserRepository;
import com.example.be.request.BlogRequest;
import com.example.be.service.BlogService;
import com.example.be.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Log4j2
public class BlogServiceImpl extends BaseServiceImpl<Blog> implements BlogService {
    public BlogServiceImpl(BaseRepository<Blog, Long> baseRepo, Utils utils) {
        super(baseRepo, utils);
    }

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogcategoryRepository blogcategoryRepository;
    public Blog createRequest(BlogRequest blogRequest, BindingResult bindingResult) {
        Blog blog = new Blog();
        mapper.map(blogRequest, blog);
        blog.setAdmin(userRepository.findUserById(blogRequest.getAdminId()));
        return blogRepository.save(blog);
    }

    public void deleteBlog(long id) {
        blogRepository.deleteById(id);
    }

    public Blog updateBlog(long id, BlogRequest blogRequest, BindingResult bindingResult) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("id not found: " + id)));
        mapper.map(blogRequest, blog);
        blog.setAdmin(userRepository.findUserById(blogRequest.getAdminId()));
        return blogRepository.save(blog);
    }

    public BlogDTO getBlogByBlogId(long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("id not found: " + id)));
        BlogDTO blogDTO = new BlogDTO();
        mapper.map(blog, blogDTO);

        return blogDTO;
    }

    public List<BlogDTO> getListBlog() {
        List<Blog> blogs = blogRepository.findAll();
        List<BlogDTO> blogDTOS = new ArrayList<>();
        for (int i = 0; i < blogs.size(); i++) {
            BlogDTO blogDTO = new BlogDTO();
            mapper.map(blogs.get(i), blogDTO);

            // Kiểm tra và khởi tạo blogcategory nếu nó là null
            if (blogDTO.getBlogcategory() == null) {
                blogDTO.setBlogcategory(new ArrayList<>());
            }

            blogs.get(i).getBlogcategorie().forEach(blogcategory -> {
                blogDTO.getBlogcategory().add(blogcategory);
            });

            blogDTOS.add(blogDTO);
        }
        return blogDTOS;
    }

     public Page<BlogDTO> filterBlogs(Pageable pageable, String category) {
        Long id = blogcategoryRepository.findBlogcategoryByName(category).getId();

         Page<Blog> blogs = blogRepository.findBlogsByCategoryId(pageable, id);
         List<BlogDTO> blogDTOS = new ArrayList<>();
         for (int i = 0; i < blogs.getContent().size(); i++) {
             BlogDTO blogDTO = new BlogDTO();
             mapper.map(blogs.getContent().get(i), blogDTO);

             // Kiểm tra và khởi tạo blogcategory nếu nó là null
             if (blogDTO.getBlogcategory() == null) {
                 blogDTO.setBlogcategory(new ArrayList<>());
             }

             blogs.getContent().get(i).getBlogcategorie().forEach(blogcategory -> {
                 blogDTO.getBlogcategory().add(blogcategory);
             });

             blogDTOS.add(blogDTO);
         }
         return new PageImpl<>(blogDTOS, pageable, blogs.getTotalElements());
     }

}
