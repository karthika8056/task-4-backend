package com.example.demo.Model;

import com.example.demo.DTO.UserOrderDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderDetails{
    @JsonIgnore
    public Long getOrderId() {
        return orderId;
    }
    @JsonIgnore
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    @JsonIgnore
    public UserDetail getUser() {
        return user;
    }
    @JsonIgnore
    public void setUser(UserDetail user) {
        this.user = user;
    }
    @JsonIgnore
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    @JsonIgnore
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    @JsonIgnore
    public Long getTotal() {
        return total;
    }
    @JsonIgnore
    public void setTotal(Long total) {
        this.total = total;
    }
    @JsonIgnore
    public List<OrderItems> getOrderItems() {
        return orderItems;
    }
    @JsonIgnore
    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private LocalDateTime orderDate;
    private Long total;
    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems = new ArrayList<>();






}
