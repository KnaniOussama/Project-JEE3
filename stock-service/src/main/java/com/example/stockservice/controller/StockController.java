package com.example.stockservice.controller;

import com.example.stockservice.model.Stock;
import com.example.stockservice.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<Stock> getAllStock() {
        return stockService.getAllStock();
    }

    @GetMapping("/product/{productId}")
    public Stock getStockByProductId(@PathVariable Long productId) {
        return stockService.getStockByProductId(productId);
    }

    @PostMapping
    public Stock saveStock(@RequestBody Stock stock) {
        return stockService.saveStock(stock);
    }
}
