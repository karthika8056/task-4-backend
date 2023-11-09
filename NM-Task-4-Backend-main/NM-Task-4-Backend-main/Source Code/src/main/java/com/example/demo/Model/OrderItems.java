package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties("OrderItems")
public class OrderItems {

    @JsonIgnore
    public Long getOrderItemId() {
        return orderItemId;
    }
    @JsonIgnore
    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }
    @JsonIgnore
    public Product getProduct() {
        return product;
    }
    @JsonIgnore
    public void setProduct(Product product) {
        this.product = product;
    }
    @JsonIgnore
    public Integer getQuantity() {
        return quantity;
    }
    @JsonIgnore
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @JsonIgnore
    public OrderDetails getOrder() {
        return order;
    }
    @JsonIgnore
    public void setOrder(OrderDetails order) {
        this.order = order;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long orderItemId;
    @JsonIgnore

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    private  Integer quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderDetails order;

}
