package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    public Category(String name, String slug, Category parentCategory) {
        this.categoryName = name;
        this.slug = slug;
        this.parentCategory = parentCategory;
    }
   public Category(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer  categoryId;
    @Column(unique = true)
    private String  categoryName;

    private  String slug;

    @ManyToOne
    @JoinColumn(name = "parent_id")
     private  Category parentCategory;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product>  productList = new ArrayList<>();
}
