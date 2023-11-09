package com.example.demo.DAO;

import com.example.demo.Model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository  extends JpaRepository<OrderItems,Integer> {
}
