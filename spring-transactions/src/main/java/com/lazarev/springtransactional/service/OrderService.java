package com.lazarev.springtransactional.service;

import com.lazarev.springtransactional.dto.ProductOrderDto;
import com.lazarev.springtransactional.entity.Order;
import com.lazarev.springtransactional.entity.Product;
import com.lazarev.springtransactional.repository.OrderRepository;
import com.lazarev.springtransactional.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("all")
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrder(ProductOrderDto dto){
        Product product = productService.saveProduct(dto);
        Order order = new Order(null, dto.quantity(), product);
        orderRepository.save(order);
    }
}
