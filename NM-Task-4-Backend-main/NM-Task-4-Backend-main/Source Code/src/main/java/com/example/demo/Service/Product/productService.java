package com.example.demo.Service.Product;

import com.example.demo.DTO.productDTO;
import com.example.demo.Model.Inventory;
import com.example.demo.Model.Product;
import com.example.demo.DAO.CategoryRepository;
import com.example.demo.DAO.InventoryRepository;
import com.example.demo.DAO.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class productService  implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Override
    public String productSave(productDTO product) {
       Integer id=categoryRepository.findParentId(product.getCategoryName());
       Integer quantity=product.getQuantity();
        Product newProduct=new Product();
        newProduct.setProduct_name(product.getProduct_name());
        newProduct.setShort_des(product.getShort_des());
        newProduct.setLong_des(product.getLong_des());
        newProduct.setPrice(product.getPrice());
        newProduct.setCategory(categoryRepository.findByCategoryId(id));
        Inventory inventory=new Inventory();
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
        newProduct.setInventory(inventory);
        productRepository.save(newProduct);
        return "success";
    }

    @Override
    public Product getProductDetails(String productName) {
     // return productRepository.findByProductName(productName);
        return  productRepository.findByProductName(productName);
    }


}


//try {
//        Product savedProduct = productRepository.save(product);
//        if (savedProduct != null) {
//        return "Product saved successfully with ID: " + savedProduct.getId();
//        } else {
//        return "Product not saved.";
//        }
//        } catch (Exception e) {
//        // Log the exception for debugging
//        e.printStackTrace();
//        return "Failed to save product: " + e.getMessage();
//        }