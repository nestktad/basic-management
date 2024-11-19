package com.vn.controller;


import com.vn.entities.Category;
import com.vn.service.CategoryService;
import com.vn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
@Autowired
private ProductService productService;

    @GetMapping(value = {"/category"})
    public String category(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "3") int size,
                           Model model) {
        Page<Category> categoryPage = categoryService.findPaginated(page, size);
        long totalProducts = productService.countProducts();
        long totalCategories = categoryService.countCategory();

        model.addAttribute("countP", totalProducts);
        model.addAttribute("countC", totalCategories);
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        return "categories";
    }
    @GetMapping(value = {"/create_category"})
    public String creteCategory(Model model) {
        model.addAttribute("category", new Category());
        return "create_category";
    }
    @PostMapping("/create_category")
    public String saveCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Category created successfully");
        return "redirect:/category";
    }
    @GetMapping("/deleteCate/{id}")
    public String deleteCategory(@PathVariable int id, RedirectAttributes redirectAttributes) {
        categoryService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Category deleted successfully");
        return "redirect:/category";
    }
    @GetMapping("/editCate/{id}")
    public String editCategory(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "edit_category";
    }
    @PostMapping("/editCate/{id}")
    public String editCategory(@PathVariable int id, @ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        category.setCategoryId(id);
        categoryService.update(category);
        redirectAttributes.addFlashAttribute("message", "Category updated successfully");
        return "redirect:/category";
    }
}
