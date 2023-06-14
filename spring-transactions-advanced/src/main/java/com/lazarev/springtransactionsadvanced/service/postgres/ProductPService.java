package com.lazarev.springtransactionsadvanced.service.postgres;

import com.lazarev.springtransactionsadvanced.dto.Product;
import com.lazarev.springtransactionsadvanced.entity.postgres.ProductP;
import com.lazarev.springtransactionsadvanced.repository.postgres.ProductPRepository;
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
public class ProductPService {
    private final ProductPRepository productRepository;

    @Transactional
    public List<ProductP> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Retryable
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ProductP getProductById(Integer id) {
        var result = productRepository.findProductById(id)
                .orElseThrow(NoSuchElementException::new);
        System.out.println(result.getAmount());
        sleep(5);
        Integer amount = productRepository.findAmount(id);
        System.out.println(amount);
        return result;
    }

    @Transactional
    public void saveProduct(Product product) {
        ProductP productP = new ProductP();
        productP.setName(product.name());
        productP.setAmount(product.amount());
        productRepository.save(productP);
    }

   // @Retryable
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateProduct(Integer id, Product product) {
        var retrievedProduct = productRepository.findProductById(id).orElseThrow(NoSuchElementException::new);
        retrievedProduct.setName(product.name());
        retrievedProduct.setAmount(product.amount());
        productRepository.save(retrievedProduct);
        sleep(7);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer getCount(){
        Integer productsCount1 = productRepository.getProductsCount();
        sleep(12);
        Integer productsCount2 = productRepository.getProductsCount();
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
