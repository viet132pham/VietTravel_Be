package com.example.be.controller;

import com.example.be.dto.BlogDTO;
import com.example.be.entity.Blog;
import com.example.be.request.BlogRequest;
import com.example.be.service.BaseService;
import com.example.be.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/blog")
public class BlogController extends BaseController<Blog> {
    public BlogController(BaseService<Blog> baseService) {
        super(baseService);
    }

    @Autowired
    private BlogService blogService;

    @PostMapping("/post")
    public Blog postRequest(@RequestBody @Valid BlogRequest blogRequest, BindingResult bindingResult) {
        return blogService.createRequest(blogRequest, bindingResult);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBlog(@PathVariable(value = "id") long id){
        blogService.deleteBlog(id);
    }

    @PutMapping("/put/{id}")
    public Blog updateBlog(@PathVariable(value = "id") long id, @RequestBody @Valid BlogRequest blogRequest, BindingResult bindingResult) {
        return blogService.updateBlog(id, blogRequest, bindingResult);
    }

    // get blog theo id blog, gen blog ra front end
    @GetMapping("/get/{id}")
    public BlogDTO getBlogByBlogId(@PathVariable(value = "id") long id){
        return blogService.getBlogByBlogId(id);
    }

    @GetMapping("/get")
    public List<BlogDTO> getListBlog(){
        return blogService.getListBlog();
    }

    @GetMapping("/filter")
    public Page<BlogDTO> filterBlogs (
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "pageNumber", required = true) int pageNumber,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        Sort.Direction sortDirection = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        if (sortBy == null) {
            sortBy = "id";
        }

        Sort sort = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return blogService.filterBlogs(pageable, category);
    }

}