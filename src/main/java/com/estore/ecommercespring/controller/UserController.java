package com.estore.ecommercespring.controller;


import com.estore.ecommercespring.dto.UserDto;
import com.estore.ecommercespring.model.User;
import com.estore.ecommercespring.service.CategoryService;
import com.estore.ecommercespring.service.ProductService;
import com.estore.ecommercespring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Controller
public class UserController {
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    public UserController(UserService userService, ProductService productService, CategoryService categoryService){
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

 @GetMapping("/")
    public ModelAndView landingPage(HttpServletRequest request){
     ModelAndView  model = new ModelAndView();
     model.setViewName("index");
     model.addObject("products", productService.getAllProducts());
     model.addObject("categories", categoryService.getAllCategory());
     HttpSession session = request.getSession();
     session.setAttribute("U_email", session.getAttribute("email"));
        return model;
 }
 @GetMapping("/register")
 public ModelAndView signUpPage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("register");
        model.addObject("user", new UserDto());
        return model;
 }
 @GetMapping("/login")
 public String loginPage(Model model){
        model.addAttribute("user", new UserDto());
        return "login";
 }

@PostMapping("/register")
public  String registerUser(@ModelAttribute("user") UserDto userDto, HttpServletRequest request){
        User user = userService.saveUser(userDto);
        if(user == null){
            String message = "Email already exists. Please try again.";
            RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
            redirectAttributes.addFlashAttribute("alertMessage", message);
            return "redirect:/register";
        }
        request.setAttribute("status", "success");
        return "login";
}
    @PostMapping("/login")
    public String logUserIn(@ModelAttribute("user") UserDto userDto, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(userDto.getUserEmail().equals("admin@gmail.com") && userDto.getPassword().equals("12345")){
            session.setAttribute("admin", userDto.getUserEmail());
            return "redirect:/admin";
        }

        User user = userService.authenticate(userDto);

        if(user == null){
            return "redirect:/";
        }


        session.setAttribute("email", userDto.getUserEmail());
        return "redirect:/";
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
       HttpSession session = request.getSession();
        session.removeAttribute("email");
        ModelAndView model = new ModelAndView("redirect:/");//This will be used in the login view
        return model;
    }

}
