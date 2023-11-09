package com.example.demo.DAO;

import com.example.demo.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    Inventory findByInventoryId(Integer id);
}
