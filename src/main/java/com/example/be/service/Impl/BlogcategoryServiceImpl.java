package com.example.be.service.Impl;

import com.example.be.entity.Blogcategory;
import com.example.be.repository.BaseRepository;
import com.example.be.service.BlogcategoryService;
import com.example.be.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BlogcategoryServiceImpl extends BaseServiceImpl<Blogcategory> implements BlogcategoryService {
    public BlogcategoryServiceImpl(BaseRepository<Blogcategory, Long> baseRepo, Utils utils) {
        super(baseRepo, utils);
    }

}
