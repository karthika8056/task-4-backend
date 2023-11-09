package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
public class Inventory {

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer inventoryId;
    private Integer quantity;
    @OneToOne(mappedBy = "inventory")
    private Product product;



}
