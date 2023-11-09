package com.example.demo.Service.Order;

import com.example.demo.DTO.PlaceOrder;
import com.example.demo.Model.OrderDetails;
import com.example.demo.Model.OrderItems;
import com.example.demo.Model.Product;
import com.example.demo.DAO.OrderDetailsRepository;
import com.example.demo.DAO.OrderItemsRepository;
import com.example.demo.DAO.ProductRepository;
import com.example.demo.DAO.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class orderService implements  OrderService{

        @Autowired
        private OrderDetailsRepository orderDetailsRepository;
        @Autowired
        private OrderItemsRepository orderItemsRepository;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private ProductRepository productRepository;

    @Override
    public String createOrderAndAddItems(PlaceOrder placeOrderRequest) {

        OrderDetails order = new OrderDetails();


        System.out.print(userRepository.findByUserName(placeOrderRequest.getUserName()));
        order.setUser(userRepository.findByUserName(placeOrderRequest.getUserName()));
        order.setOrderDate(LocalDateTime.now());

        List<OrderItems> orderItemsList = new ArrayList<>();
        List<String>products=placeOrderRequest.getProduct();
        List<Integer>quantities=placeOrderRequest.getQuantities();
        long total = 0;
        for (int i = 0; i < placeOrderRequest.getProduct().size(); i++) {
            Product product_id=productRepository.findByProductName(products.get(i));
             int quantity = quantities.get(i);

                OrderItems orderItem = new OrderItems();
                orderItem.setProduct(product_id);
                if(product_id.getInventory().getQuantity()<quantity){
                   return "Out of Stock";
                }
                Integer final_quantity=  product_id.getInventory().getQuantity()-quantity;
                 product_id.getInventory().setQuantity(final_quantity);
                orderItem.setQuantity(quantity);
                orderItem.setOrder(order);

                long itemPrice =product_id.getPrice()* quantity;
                total += itemPrice;

                orderItemsList.add(orderItem);
            }

            order.setTotal(total);
            order.setOrderItems(orderItemsList);
            orderDetailsRepository.save(order);

        return "Success";
    }
}
