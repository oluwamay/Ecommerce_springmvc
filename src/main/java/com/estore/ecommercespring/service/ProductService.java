package com.estore.ecommercespring.service;

import com.estore.ecommercespring.dto.ProductDto;
import com.estore.ecommercespring.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    void addProduct(ProductDto productDto);
    void removeProduct(Long id);
    void updateProduct(Product product);
    Product getProduct(Long id);
    List<Product> getAllProductByCategory(Long id);
}
