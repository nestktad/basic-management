package com.vn.dto;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductWithCategoryDTO {
    private Integer productId;
    private String productName;
    private float price;
    private int stock;
    private String image;
    private Integer categoryId;
    private String categoryName;

    public ProductWithCategoryDTO(Integer productId, String productName, float price, int stock,
                                  String image, Integer categoryId, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
