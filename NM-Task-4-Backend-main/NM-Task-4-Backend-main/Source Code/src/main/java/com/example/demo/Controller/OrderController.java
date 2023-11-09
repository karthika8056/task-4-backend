package com.example.demo.Controller;

import com.example.demo.DTO.PlaceOrder;
import com.example.demo.Service.Order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping("place-order")
    public ResponseEntity<String> placeOrder(
            @RequestBody PlaceOrder placeOrderRequest
    ) {
        return new ResponseEntity<>( orderService.createOrderAndAddItems(placeOrderRequest), HttpStatus.CREATED);
    }
}
