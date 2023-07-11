package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "blogcategory")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Blogcategory extends BaseEntity{
    @NotNull
    private String name;
}
