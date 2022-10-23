package com.lazarev.hibernate;

import com.lazarev.hibernate.config.AppConfiguration;
import com.lazarev.hibernate.entity.Product;
import com.lazarev.hibernate.repository.ProductRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class ApplicationMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);


        ProductRepository repository = context.getBean(ProductRepository.class);
        Product product = new Product(null, "Milk", 150, LocalDate.of(2022, 5, 31));
        repository.saveProduct(product);
        System.out.println(repository.getProductById(1L));
        context.close();
    }
}
