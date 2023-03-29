package com.estore.ecommercespring.controller;


import com.estore.ecommercespring.dto.UserDto;
import com.estore.ecommercespring.model.User;
import com.estore.ecommercespring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
 @GetMapping("/")
    public String loginPage(Model model){
        UserDto user = new UserDto();
        model.addAttribute(user);
        return "login";
 }
 @GetMapping("/registration")
 public String signUpPage(ModelAndView model){
        model.addObject("user", new UserDto());
        return "registration";
 }
@PostMapping("/register")
public  String registerUser(@ModelAttribute("user") UserDto userDto, HttpServletRequest request){
        User user = userService.saveUser(userDto);
        if(user == null){
            request.setAttribute("status", "failed");
            return "registration";
        }
        request.setAttribute("status", "success");
        return "login";
}
    @PostMapping("/login")
    public String logUserIn(@ModelAttribute("user") UserDto userDto, HttpServletRequest request){
        User user = userService.authenticate(userDto.getUserEmail(), userDto.getPassword());
        if(user == null){
            request.setAttribute("status", "failed");
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("email", userDto.getUserEmail());
        return "home";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
       HttpSession session = request.getSession();
        session.removeAttribute("email");
        return "login";
    }
}