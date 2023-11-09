package com.example.demo.Controller;

import com.example.demo.Model.Category;
import com.example.demo.Service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class categoryController {
    @Autowired
    private CategoryService categoryService;
      @PostMapping("addCategory")
    public String add(@RequestBody Category category){
          return categoryService.addCategory(category);

      }
      @GetMapping("mainCategory")
    public List<String> view(){
          return categoryService.displayMainCategory();
      }
      @GetMapping("subCategory/{mainCategory}")
    public List<String>viewSunCategory(@PathVariable String mainCategory){
          return  categoryService.displaySubCategory(mainCategory);
      }



}
