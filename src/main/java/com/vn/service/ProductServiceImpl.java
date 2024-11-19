package com.vn.service;

import com.vn.dto.ProductWithCategoryDTO;
import com.vn.entities.Product;
import com.vn.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Sử dụng @Service để phù hợp với vai trò của lớp
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Lấy danh sách tất cả sản phẩm
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Lấy sản phẩm theo ID
    @Override
    public Product getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null); // Trả về null nếu không tìm thấy
    }

    // Xóa sản phẩm theo ID
    @Override
    public void deleteProductById(int id) {
        if (productRepository.existsById(id)) { // Kiểm tra sản phẩm có tồn tại trước khi xóa
            productRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
        }
    }

    // Cập nhật thông tin sản phẩm
    @Override
    public Product updateProduct(Product product) {
        if (product.getProductId() == null || !productRepository.existsById(product.getProductId())) {
            throw new IllegalArgumentException("Cannot update product. Product does not exist or ID is missing.");
        }
        return productRepository.save(product); // Lưu lại sản phẩm sau khi cập nhật
    }

    // Thêm mới hoặc lưu sản phẩm
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public long countProducts() {
        return productRepository.count();
    }

    @Override
    public Page<ProductWithCategoryDTO> getAllProductsWithCategoryS(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Object[]> results = productRepository.findAllWithCategoryS(pageable);

        return results.map(row -> new ProductWithCategoryDTO(
                (Integer) row[0],
                (String) row[1],
                (Float) row[2],
                (Integer) row[3],
                (String) row[4],
                (Integer) row[5],
                (String) row[6]
        ));
    }


}
