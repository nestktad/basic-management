package com.vn.service;

import com.vn.entities.Category;
import com.vn.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Category findById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public Category save(Category category) {
        if (category.getCategoryId() != null) {
            throw new IllegalArgumentException("New Category cannot have an ID.");
        }
        return categoryRepository.save(category);
    }

    // Cập nhật danh mục
    @Override
    public Category update(Category category) {
        if (category.getCategoryId() == null || !categoryRepository.existsById(category.getCategoryId())) {
            throw new IllegalArgumentException("Cannot update Category. ID is missing or does not exist.");
        }
        return categoryRepository.save(category);
    }

    // Xóa danh mục theo ID
    @Override
    public void deleteById(int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist.");
        }
    }

    @Override
    public long countCategory() {
        return categoryRepository.count();
    }

    @Override
    public Page<Category> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable);
    }
}
