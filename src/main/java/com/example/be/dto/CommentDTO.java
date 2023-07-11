package com.example.be.dto;

import com.example.be.entity.compositekey.CommentComposite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class CommentDTO {
    private CommentComposite id ;
    private BlogDTO blogDTO;
    private UserDTO userDTO;
    private String content;
    private long parentId;
}
