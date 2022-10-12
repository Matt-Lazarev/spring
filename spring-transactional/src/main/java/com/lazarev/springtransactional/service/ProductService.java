package com.lazarev.springtransactional.service;

import com.lazarev.springtransactional.entity.Product;
import com.lazarev.springtransactional.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void saveProducts(List<Product> products) {
        for (Product product : products) {
            product.setName(product.getName().toUpperCase());
            productRepository.save(product);
        }
    }
}

class ProxyProductService extends ProductService {

    @Override
    public void saveProducts(List<Product> products) {
        startTransaction();
        try {
            super.saveProducts(products);
            commitTransaction();
        } catch (Throwable throwable) {
            rollbackTransaction();
        }
    }

    public ProxyProductService(ProductRepository productRepository) {
        super(productRepository);
    }

    private void startTransaction() {}

    private void commitTransaction() {}

    private void rollbackTransaction() {}
}
