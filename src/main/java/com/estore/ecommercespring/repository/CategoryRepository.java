package com.estore.ecommercespring.repository;

import com.estore.ecommercespring.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
