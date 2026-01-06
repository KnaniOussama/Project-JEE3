package com.example.stockservice.client;

import com.example.stockservice.dto.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/products/{id}")
    @CircuitBreaker(name = "productService", fallbackMethod = "getProductByIdFallback")
    Product getProductById(@PathVariable("id") Long id);

    @Component
    class ProductClientFallback implements ProductClient {
        @Override
        public Product getProductById(Long id) {
            return new Product(id, "Fallback Product", 0.0);
        }
    }
}
