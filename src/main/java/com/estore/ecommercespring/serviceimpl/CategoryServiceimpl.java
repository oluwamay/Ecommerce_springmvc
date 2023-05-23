package com.estore.ecommercespring.serviceimpl;

import com.estore.ecommercespring.model.Category;
import com.estore.ecommercespring.repository.CategoryRepository;
import com.estore.ecommercespring.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceimpl implements CategoryService {
private final CategoryRepository categoryRepository;

public CategoryServiceimpl(CategoryRepository categoryRepository){
    this.categoryRepository = categoryRepository;
}
public Category addCategory(Category category){
   return categoryRepository.save(category);
}
public List<Category> getAllCategory(){
    return categoryRepository.findAll();
}

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category editCateg(Long id, Category category) {
         Category categoryDB = categoryRepository.findById(id).get();
         categoryDB.setName(category.getName());
         return categoryRepository.save(categoryDB);
    }

    @Override
    public Optional<Category> findCategory(Long id) {
        return categoryRepository.findById(id);
    }

}
