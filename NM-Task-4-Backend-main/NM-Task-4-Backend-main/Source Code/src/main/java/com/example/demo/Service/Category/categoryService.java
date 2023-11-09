package com.example.demo.Service.Category;

import com.example.demo.Model.Category;
import com.example.demo.DAO.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryService implements  CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;;
    @Override
    public String addCategory(Category category) {
        if (category != null && category.getParentCategory() != null && category.getParentCategory().getCategoryName() != null) {

            if (categoryRepository.findByCategoryName(category.getParentCategory().getCategoryName()).isPresent()) {
                Category newCategory = new Category();
                newCategory.setCategoryName(category.getCategoryName());
                newCategory.setSlug(category.getSlug());
                System.out.println(category.getParentCategory().getCategoryName());
                Integer id=categoryRepository.findParentId(category.getParentCategory().getCategoryName());

                System.out.println(categoryRepository.findByCategoryId(id));
                newCategory.setParentCategory((Category) categoryRepository.findByCategoryId(id));

                categoryRepository.save(newCategory);
                return "Success";
            }
        } else {
            System.out.println("else");
            categoryRepository.save(category);
        }
        return "Success";}

    @Override
    public List<String> displayMainCategory() {
       return categoryRepository.findParentCategory();
    }

    @Override
    public List<String> displaySubCategory(String mainCategory) {
        return categoryRepository.findChildCategory(categoryRepository.findParentId(mainCategory));
    }


}

