package com.example.be.controller;

import com.example.be.entity.Blogcategory;
import com.example.be.service.BaseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/blogcategory")
public class BlogcategoryController extends BaseController<Blogcategory> {
    public BlogcategoryController(BaseService<Blogcategory> baseService) {
        super(baseService);
    }

}