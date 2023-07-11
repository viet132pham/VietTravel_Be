package com.example.be.request;

import com.example.be.entity.compositekey.CommentComposite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private CommentComposite id ;

    private String content;

    private long userId;

    private long blogId;

    private long parentId;
}
