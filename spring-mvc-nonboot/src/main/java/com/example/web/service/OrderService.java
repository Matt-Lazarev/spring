package com.example.web.service;

import com.example.web.entity.Order;
import com.example.web.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    // %d - число
    // %f - дробь
    // %s - строка
    public Order getOrderById(Integer id){
        return orderRepository.getOrderById(id)
                .orElseThrow(()-> new NoSuchElementException(
                        "Order with id='%d' not found".formatted(id)));
    }
    //10/3 = 3.3333331   64
    //10/3 = 11.110 110 110 110 1

    public void saveOrder(Order order){
        BigDecimal price = order.getPrice();
        Integer amount = order.getAmount();
        BigDecimal totalPrice = price.multiply(new BigDecimal(amount));
        order.setTotalPrice(totalPrice);

        orderRepository.saveOrder(order);
    }

    public void updateOrder(Order order, Integer orderId){
        orderRepository.updateOrder(order, orderId);
    }

    public void deleteOrder(Integer orderId){
        orderRepository.deleteOrder(orderId);
    }
}
