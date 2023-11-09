package com.example.demo.DAO;

import com.example.demo.Model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Integer> {
   ConfirmationToken findByConfirmationToken(String confirmationToken);
}
