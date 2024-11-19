package com.vn.controller;

import com.vn.dto.ProductWithCategoryDTO;
import com.vn.entities.Category;
import com.vn.entities.Product;
import com.vn.service.CategoryService;
import com.vn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping(value = {"/", "/index", "/home"})
    public String index(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<ProductWithCategoryDTO> products = productService.getAllProductsWithCategoryS(page, size);
        long totalProducts = productService.countProducts();
        long totalCategories = categoryService.countCategory();

        model.addAttribute("countP", totalProducts);
        model.addAttribute("countC", totalCategories);
        model.addAttribute("products", products.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        return "products";
    }

    @GetMapping(value = {"/create_product"})
    public String createProduct(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        return "create_product";
    }

    @PostMapping(value = {"/create_product"})
    public String saveProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm thành công!");
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        productService.deleteProductById(id);
        redirectAttributes.addFlashAttribute("message", "Xóa sản phẩm thành công!");
        return "redirect:/home";
    }

    @PostMapping(value = {"/edit/{id}"})
    public String updateProduct(@ModelAttribute Product product, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        product.setProductId(id);
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("message", "Cập nhật sản phẩm thành công!");
        return "redirect:/home";
    }

    @GetMapping(value = {"/edit/{id}"})
    public String editProduct(@PathVariable("id") Integer id, Model model) {
        Product product = productService.getProductById(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "edit_product"; // Trang chỉnh sửa sản phẩm
    }
}


