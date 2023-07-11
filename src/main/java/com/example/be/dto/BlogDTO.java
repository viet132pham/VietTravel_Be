package com.example.be.dto;

import com.example.be.entity.Blogcategory;
import com.example.be.entity.compositekey.CommentComposite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class BlogDTO {
    private String id;
    private String title;
    private String description;
    private String image;
    private ArrayList<Blogcategory> blogcategory;
}
