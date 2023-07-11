package com.example.be.entity;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {
    @NotNull
    private String roleName;
    @NotNull
    private String roleCode;
}
