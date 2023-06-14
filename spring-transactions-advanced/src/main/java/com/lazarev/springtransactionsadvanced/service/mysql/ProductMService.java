package com.lazarev.springtransactionsadvanced.service.mysql;

import com.lazarev.springtransactionsadvanced.dto.Product;
import com.lazarev.springtransactionsadvanced.entity.mysql.ProductM;
import com.lazarev.springtransactionsadvanced.repository.mysql.ProductMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductMService {
    private final ProductMRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductM> getAllProducts(){
        return productRepository.findAllProducts();
    }

    @Transactional(transactionManager = "mysqlTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public ProductM getProductById(Integer id){
        //sleep(8);
        return productRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional(transactionManager = "mysqlTransactionManager")
    public void saveProduct(Product product){
        ProductM productM = new ProductM();
        productM.setName(product.name());
        productM.setAmount(product.amount());
        productRepository.save(productM);
    }

    //@Retryable
    @Transactional(transactionManager = "mysqlTransactionManager", isolation = Isolation.READ_COMMITTED)
    public void updateProduct(Integer id, Product product) {
        ProductM retrievedProduct = productRepository.findProductById(id)
                .orElseThrow(NoSuchElementException::new);
        retrievedProduct.setName(product.name());
        retrievedProduct.setAmount(product.amount());
        productRepository.save(retrievedProduct);
        sleep(10);
        //throw new RuntimeException();
    }

    @Transactional(transactionManager = "mysqlTransactionManager", isolation = Isolation.REPEATABLE_READ)
    public Integer getCount(){
        Integer productsCount1 = productRepository.getProductsCount();
        sleep(12);
        //productRepository.updateAmountByName("Test", new Random().nextInt(100));
        Integer productsCount2 = productRepository.getProductsCount();
        System.out.println(productsCount1);
        System.out.println(productsCount2);
        return productsCount1;
    }

    private void sleep(int seconds){
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
