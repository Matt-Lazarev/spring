package com.lazarev.springtransactionsadvanced.controller;

import com.lazarev.springtransactionsadvanced.dto.Product;
import com.lazarev.springtransactionsadvanced.service.mysql.ProductMService;
import com.lazarev.springtransactionsadvanced.service.postgres.ProductPService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class TransactionController {
    private final ProductMService productMService;
    private final ProductPService productPService;

    @GetMapping("/{productVersion}")
    public List<?> getProducts(@PathVariable String productVersion) {
        return switch (productVersion){
            case "M" -> productMService.getAllProducts();
            case "P" -> productPService.getAllProducts();
            default -> List.of();
        };
    }

    @GetMapping("/{productVersion}/count")
    public Integer getProductsCount(@PathVariable String productVersion) {
        return switch (productVersion){
            case "M" -> productMService.getCount();
            case "P" -> productPService.getCount();
            default -> 0;
        };
    }

    @GetMapping("/{productVersion}/{id}")
    public Object getProductById(@PathVariable String productVersion,
                                 @PathVariable Integer id) {
        return switch (productVersion){
            case "M" -> productMService.getProductById(id);
            case "P" -> productPService.getProductById(id);
            default -> List.of();
        };
    }

    @PostMapping("/{productVersion}")
    public void saveProduct(@RequestBody Product product,
                            @PathVariable String productVersion){
        switch (productVersion){
            case "M" -> productMService.saveProduct(product);
            case "P" -> productPService.saveProduct(product);
        }
    }

    @PutMapping("/{productVersion}/{id}")
    public void updateProductM(@PathVariable String productVersion,
                               @PathVariable Integer id,
                               @RequestBody Product product){
        switch (productVersion){
            case "M" -> productMService.updateProduct(id, product);
            case "P" -> productPService.updateProduct(id, product);
        }
    }
}
