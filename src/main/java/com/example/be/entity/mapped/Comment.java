package com.example.be.entity.mapped;

import com.example.be.entity.Blog;
import com.example.be.entity.User;
import com.example.be.entity.compositekey.CommentComposite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @EmbeddedId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private CommentComposite id ;

    @ManyToOne
    @MapsId("blogId")
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String content;

    private Long parentId;

}
