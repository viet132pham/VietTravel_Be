package com.example.be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "blog")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Blog extends BaseEntity{
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String image;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "blog_blogcategory", joinColumns = {@JoinColumn(name = "blog_id")}, inverseJoinColumns = {@JoinColumn(name = "blogcategory_id")})
    private Set<Blogcategory> blogcategorie = new HashSet<>();
}
