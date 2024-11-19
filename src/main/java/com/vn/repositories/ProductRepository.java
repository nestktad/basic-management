package com.vn.repositories;

import com.vn.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT p.ProductID, p.ProductName, p.Price, p.Stock, p.Image, " +
            "p.CategoryID, c.CategoryName " +
            "FROM Product p " +
            "LEFT JOIN Category c ON p.CategoryID = c.CategoryID",
            nativeQuery = true)
    Page<Object[]> findAllWithCategoryS(Pageable pageable);

}
