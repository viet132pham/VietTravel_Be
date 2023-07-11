package com.example.be.entity;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "amenitytour")
@Getter
@Setter
public class Amenitytour extends BaseEntity {
    @NotNull
    private String title;
    @NotNull
    private String name;

    @NotNull
    private int status;
}
