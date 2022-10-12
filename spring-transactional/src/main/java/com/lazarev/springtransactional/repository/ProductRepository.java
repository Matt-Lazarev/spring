package com.lazarev.springtransactional.repository;

import com.lazarev.springtransactional.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
