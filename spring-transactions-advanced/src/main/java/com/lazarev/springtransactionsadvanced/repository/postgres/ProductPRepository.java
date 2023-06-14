package com.lazarev.springtransactionsadvanced.repository.postgres;

import com.lazarev.springtransactionsadvanced.entity.postgres.ProductP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface ProductPRepository extends JpaRepository<ProductP, Integer> {
    //@Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select p from ProductP p")
    List<ProductP> findAllProducts();

    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ProductP p where p.id = :id")
    Optional<ProductP> findProductById(Integer id);

    @Query("select p.amount from ProductP p where p.id = :id")
    Integer findAmount(Integer id);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select count(p) from ProductP p")
    Integer getProductsCount();

    @Modifying
    @Query("update ProductP p set p.amount = :amount where p.name = :name")
    void updateAmountByName(String name, int amount);
}
