package com.example.stockservice.service;

import com.example.stockservice.client.ProductClient;
import com.example.stockservice.dto.Product;
import com.example.stockservice.model.Stock;
import com.example.stockservice.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final ProductClient productClient;

    public StockService(StockRepository stockRepository, ProductClient productClient) {
        this.stockRepository = stockRepository;
        this.productClient = productClient;
    }

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public Stock getStockByProductId(Long productId) {
        Product product = productClient.getProductById(productId);
        if (product == null) {
            return null;
        }
        Optional<Stock> stock = stockRepository.findByProductId(productId);
        return stock.orElse(null);
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }
}
