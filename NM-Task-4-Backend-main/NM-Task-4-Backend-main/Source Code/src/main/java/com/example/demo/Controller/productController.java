package com.example.demo.Controller;


import com.example.demo.DTO.productDTO;
import com.example.demo.DTO.productShortDescription;
import com.example.demo.Model.Product;
import com.example.demo.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class productController {

    @Autowired
    private ProductService productService;
    @PostMapping("save/product")
    public ResponseEntity<String> productDetails(@RequestBody productDTO product) {
        try {
            String result = productService.productSave(product);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("product/{productName}")
    public Product getProductDetails(@PathVariable  String productName){
        return  productService.getProductDetails(productName);
    }

}
