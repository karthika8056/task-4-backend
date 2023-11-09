//package com.example.demo;
//
//public class examlple {
//    @Service
//    public class OrderService {
//
//        @Autowired
//        private OrderRepository orderRepository;
//
//        @Autowired
//        private OrderItemsRepository orderItemsRepository;
//
//        @Transactional
//        public Order createOrderAndAddItems(UserDetail user, List<Product> products, List<Integer> quantities) {
//            // Create a new order
//            Order order = new Order();
//            order.setUser(user);
//            order.setOrderDate(LocalDateTime.now());
//            order.setTotal(0L);
//
//            // Create a list to store order items
//            List<OrderItems> orderItemsList = new ArrayList<>();
//
//            // Calculate the total price and create order items
//            long total = 0;
//            for (int i = 0; i < products.size(); i++) {
//                Product product = products.get(i);
//                int quantity = quantities.get(i);
//
//                OrderItems orderItem = new OrderItems();
//                orderItem.setProduct(product);
//                orderItem.setQuantity(quantity);
//                orderItem.setOrder(order);
//
//                long itemPrice = product.getPrice() * quantity;
//                total += itemPrice;
//
//                // Add the order item to the list
//                orderItemsList.add(orderItem);
//            }
//
//            // Set the total price
//            order.setTotal(total);
//
//            // Set the list of order items in the order
//            order.setOrderItems(orderItemsList);
//
//            // Save the order, which will also save the associated order items
//            order = orderRepository.save(order);
//
//            return order;
//        }
//    }
//
//}
