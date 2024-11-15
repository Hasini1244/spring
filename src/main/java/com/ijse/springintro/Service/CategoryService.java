package com.ijse.springintro.Service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.springintro.Entity.Category;


@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category getCategoryById(Long id);
}