package com.example.productservice.mcp;

import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTools {

    private final ProductService productService;

    public ProductTools(ProductService productService) {
        this.productService = productService;
    }

    @McpTool(name = "getAllProducts", description = "Get all products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @McpTool(name = "getProductById", description = "Get a product by its ID")
    public Product getProductById(long id) {
        return productService.getProductById(id);
    }
}
