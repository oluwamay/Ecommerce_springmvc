package com.estore.ecommercespring.controller;

import com.estore.ecommercespring.global.GlobalCart;
import com.estore.ecommercespring.global.GlobalWishList;
import com.estore.ecommercespring.service.CategoryService;
import com.estore.ecommercespring.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public HomeController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }
    @GetMapping("/index")
    public ModelAndView userHomePage(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        ModelAndView model = new ModelAndView();
        String userEmail = (String) session.getAttribute("U_email");
        if(userEmail == null){
            model.setViewName("redirect:/");
            return model;
        }
        model.setViewName("index");
        model.addObject("products", productService.getAllProducts());
        model.addObject("categories", categoryService.getAllCategory());

        return model;
    }
    @GetMapping("/index/category/{id}")
    public ModelAndView getProductsByCategory(@PathVariable("id") Long id, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        ModelAndView model = new ModelAndView();
        String userEmail = (String) session.getAttribute("U_email");
        if(userEmail == null){
            model.setViewName("redirect:/");
            return model;
        }
        System.out.println(productService.getAllProductByCategory(id));
        model.setViewName("index");
        model.addObject("products", productService.getAllProductByCategory(id));
        model.addObject("categories", categoryService.getAllCategory());

            return  model;
    }
    @GetMapping("/addToCart/{id}")
    public ModelAndView addProductToCart(@PathVariable("id") Long id, HttpServletRequest request){
        HttpSession session = request.getSession();
        ModelAndView model = new ModelAndView();
        String userEmail = (String) session.getAttribute("U_email");
        if(userEmail == null){
            model.setViewName("redirect:/");
            return model;
        }
        GlobalCart.cart.add(productService.getProduct(id));
        model.setViewName("redirect:/index");
        return model;
    }
    @GetMapping("/cart")
    public ModelAndView viewCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        ModelAndView model = new ModelAndView();
        String userEmail = (String) session.getAttribute("U_email");
        if(userEmail == null){
            model.setViewName("redirect:/");
            return model;
        }
        model.setViewName("userCart");
        model.addObject("cartItems", GlobalCart.cart);
        model.addObject("cartCount", GlobalCart.cart.size());
        model.addObject("cartTotalAmount", GlobalCart.cart.stream().mapToDouble(product -> Double.parseDouble(product.getPrice())).sum());
        return model;
    }
    @GetMapping("/addToWishList/{id}")
    public ModelAndView addProductToWishList(@PathVariable("id") Long id, HttpServletRequest request){
        HttpSession session = request.getSession();
        ModelAndView model = new ModelAndView();
        String userEmail = (String) session.getAttribute("U_email");
        if(userEmail == null){
            model.setViewName("redirect:/");
            return model;
        }
        GlobalWishList.wishList.add(productService.getProduct(id));
        model.setViewName("redirect:/index");
        return model;
    }
    @GetMapping("/wishList")
    public ModelAndView viewList(HttpServletRequest request){
        HttpSession session = request.getSession();
        ModelAndView model = new ModelAndView();
        String userEmail = (String) session.getAttribute("U_email");
        if(userEmail == null){
            model.setViewName("redirect:/");
            return model;
        }
        model.setViewName("wishList");
        model.addObject("wishItems", GlobalWishList.wishList);
        model.addObject("wishItemsCount", GlobalWishList.wishList.size());

        return model;
    }
}
