package com.vn.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ProductId;
    private String ProductName;
    private float Price;
    private int Stock;
  //  private Integer CategoryId;
    private String Image;

    @ManyToOne
    @JoinColumn(name = "CategoryID") // Ánh xạ đến cột CategoryID trong bảng Product
    private Category category;


}
