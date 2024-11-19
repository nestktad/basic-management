package com.vn.service;

import com.vn.entities.Category;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    Category save(Category category);
    Category update(Category category);
    void deleteById(int id);
    public long countCategory();
    Page<Category>  findPaginated(int page, int size);

}
