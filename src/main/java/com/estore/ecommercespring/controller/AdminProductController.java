package com.estore.ecommercespring.controller;

import com.estore.ecommercespring.dto.ProductDto;
import com.estore.ecommercespring.model.Product;
import com.estore.ecommercespring.service.CategoryService;
import com.estore.ecommercespring.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products/add")
    public ModelAndView addProduct(){
        ModelAndView model = new ModelAndView();
        model.addObject("product", new ProductDto());
        model.addObject("categories", categoryService.getAllCategory());
        model.setViewName("addProducts");
        return model;
    }
    @GetMapping("/allProducts")
    public ModelAndView getAllProducts(){
        ModelAndView model = new ModelAndView();
        model.addObject("products", productService.getAllProducts());
        model.setViewName("allProducts");
        return model;
    }
    @GetMapping("admin/edit/{id}")
    public ModelAndView editProducts(@PathVariable("id") Long Id){
        ModelAndView model = new ModelAndView();
        Product product = productService.getProduct(Id);

        model.addObject("product", new ProductDto(
                product.getProduct_name(),
                product.getPrice(),
                product.getImageName(),
                product.getProduct_id(),
                product.getCategory().getId(),
                product.getDescription()
        ));

        model.addObject("categories", categoryService.getAllCategory());
        model.setViewName("edit");
        return model;
    }
    @GetMapping("admin/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") Long id){
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/allProducts");
        productService.removeProduct(id);
        return model;
    }

    @PostMapping("/products/add")
    public String addAProduct(@ModelAttribute("product") ProductDto productDto){
        productService.addProduct(productDto);
        return "redirect:/allProducts";
    }
    @PostMapping("admin/edit/{id}")
    public ModelAndView editAProduct(@PathVariable("id") Long Id, @ModelAttribute("product") ProductDto productDto){
        Product product = productService.getProduct(Id);

        product.setProduct_name(productDto.getName());
        product.setCategory(categoryService.findCategory(productDto.getCategory_id()).get());
        product.setDescription(productDto.getPrice());
        product.setPrice(productDto.getPrice());
        product.setImageName(productDto.getImageName());

        productService.updateProduct(product);
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/allProducts");

        return model;
    }
}
