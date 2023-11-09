package com.example.demo.DTO;

import java.util.List;

public class PlaceOrder {



    private String UserName;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public List<String> getProduct() {
        return product;
    }


    public void setProduct(List<String> product) {
        this.product = product;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }

    private List<String> product;
    private List<Integer> quantities;
}
