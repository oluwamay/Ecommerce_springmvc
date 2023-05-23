package com.estore.ecommercespring.serviceimpl;

import com.estore.ecommercespring.dto.ProductDto;
import com.estore.ecommercespring.model.Category;
import com.estore.ecommercespring.model.Product;
import com.estore.ecommercespring.repository.CategoryRepository;
import com.estore.ecommercespring.repository.ProductRepository;
import com.estore.ecommercespring.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceimpl implements ProductService{
   private final ProductRepository productRepository;
   private final CategoryRepository categoryRepository;

    public ProductServiceimpl(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public void addProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategory_id()).get();
        Product product = Product.builder()
                .product_name(productDto.getName())
                .category(category)
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .imageName(productDto.getImageName())
                .build();
        productRepository.save(product);
    }
    public void updateProduct(Product product){
        productRepository.save(product);
    }

    @Override
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAllProductByCategory(Long id) {
        return productRepository.findAllByCategoryId(id);
    }

}
