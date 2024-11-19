package com.vn.service;

import com.vn.dto.ProductWithCategoryDTO;
import com.vn.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();          // Lấy tất cả sản phẩm
    Product getProductById(int id);          // Lấy sản phẩm theo ID
    void deleteProductById(int id);          // Xóa sản phẩm theo ID
    Product updateProduct(Product product);  // Cập nhật sản phẩm và trả về kết quả
    Product saveProduct(Product product);    // Thêm mới hoặc lưu sản phẩm
    public long countProducts();
    Page<ProductWithCategoryDTO> getAllProductsWithCategoryS(int page, int size);

}
