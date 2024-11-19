package com.vn.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CategoryId;
    private String CategoryName;
    private String Description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
