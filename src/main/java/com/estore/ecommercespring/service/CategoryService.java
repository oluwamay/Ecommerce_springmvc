package com.estore.ecommercespring.service;

import com.estore.ecommercespring.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category addCategory(Category category);
    List<Category> getAllCategory();
    void deleteCategory(Long id);

    Category editCateg(Long id, Category category);
    Optional<Category> findCategory(Long id);

    }
