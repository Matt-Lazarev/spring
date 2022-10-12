package com.lazarev.springtransactional;

import com.lazarev.springtransactional.entity.Product;
import com.lazarev.springtransactional.jdbc.Person;
import com.lazarev.springtransactional.jdbc.PersonRepository;
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

        Product p1 = new Product(null, "Milk", 100);
        Product p2 = new Product(null, "Bread", 50);
        Product p3 = new Product(null, null, 250);
        productService.saveProducts(List.of(p1,p2,p3));

        productService.getAllProducts().forEach(System.out::println);
        context.close();


//        PersonRepository personRepository = new PersonRepository();
//        personRepository.savePerson(Person.builder().name("Mike").age(20).build());
    }

}
