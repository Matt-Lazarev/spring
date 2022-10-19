package com.lazarev.springtransactional;

import com.lazarev.springtransactional.dto.ProductOrderDto;
import com.lazarev.springtransactional.entity.Product;
import com.lazarev.springtransactional.jdbc.Person;
import com.lazarev.springtransactional.jdbc.PersonRepository;
import com.lazarev.springtransactional.service.OrderService;
import com.lazarev.springtransactional.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringTransactionalApplication {

    public static void main(String[] args) throws Exception {
        var context = SpringApplication.run(SpringTransactionalApplication.class, args);

        ProductService productService = context.getBean(ProductService.class);
        OrderService orderService = context.getBean(OrderService.class);

        var dto = new ProductOrderDto("Coffee", 100, 5);
        orderService.saveOrder(dto);

        productService.updateProductNameById("COFFEE", 1L);
        context.close();
    }
}
