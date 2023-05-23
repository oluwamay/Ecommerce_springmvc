package com.estore.ecommercespring.controller;

import com.estore.ecommercespring.model.Category;
import com.estore.ecommercespring.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin")
    public ModelAndView adminPage(){
        ModelAndView model = new ModelAndView("admin");
        return model;
    }
   @GetMapping("/admin/categories")
   public ModelAndView  getCategories(){
    ModelAndView model = new ModelAndView();
       List<Category> allCategory = categoryService.getAllCategory();
       model.addObject("categories", allCategory);
    model.setViewName("category");
   return model;
    }
   @GetMapping("/admin/categories/add")
    public ModelAndView addCategory(){
        ModelAndView model = new ModelAndView();
        model.addObject("category", new Category());
        model.setViewName("addCategory");
        return model;
   }
   @PostMapping("/admin/categories/add")
    public ModelAndView addCategoryToRepo(@ModelAttribute("category") Category category){
        ModelAndView model = new ModelAndView("redirect:/admin/categories");
        categoryService.addCategory(category);
        return model;
   }
   @GetMapping("/admin/category/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable("id") Long id){
    ModelAndView model = new ModelAndView();
    model.setViewName("redirect:/admin/categories");
    categoryService.deleteCategory(id);
    return model;
   }
    @GetMapping("/admin/category/edit/{id}")
    public ModelAndView editCategory(@PathVariable("id") Long id){
        ModelAndView model =new ModelAndView();
        Optional<Category> category = categoryService.findCategory(id);
       if(category.isPresent()){
           model.setViewName("redirect:/admin/categories/add");
           model.addObject("categories", category.get());
           return model;
       }
        model.setViewName("redirect:/admin/categories");
        return model;
    }
    @PostMapping("/admin/category/edit/{id}")
    public ModelAndView editExistingCategory(@PathVariable Long id, @ModelAttribute("categories") Category category){
        ModelAndView model = new ModelAndView("redirect:/admin/categories");
        categoryService.editCateg(id, category);
        return model;
    }

}
