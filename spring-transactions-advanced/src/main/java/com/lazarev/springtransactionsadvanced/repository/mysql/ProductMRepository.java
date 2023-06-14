package com.lazarev.springtransactionsadvanced.repository.mysql;

import com.lazarev.springtransactionsadvanced.entity.mysql.ProductM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface ProductMRepository extends JpaRepository<ProductM, Integer> {
    @Query("select p from ProductM p")
    List<ProductM> findAllProducts();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ProductM p where p.id=:id")
    Optional<ProductM> findProductById(Integer id);

    @Query("select count(p) from ProductM p")
    Integer getProductsCount();

    @Modifying
    @Query("update ProductM p set p.amount = :amount where p.name = :name")
    void updateAmountByName(String name, int amount);
}
