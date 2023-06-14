package com.lazarev.springtransactional.repository;

import com.lazarev.springtransactional.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("update Product p set p.name = :name where p.id=:id")
    void updateProductNameById(String name, Long id);
}
