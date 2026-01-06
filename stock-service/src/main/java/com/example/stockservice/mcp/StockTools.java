package com.example.stockservice.mcp;

import com.example.stockservice.model.Stock;
import com.example.stockservice.service.StockService;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

@Component
public class StockTools {

    private final StockService stockService;

    public StockTools(StockService stockService) {
        this.stockService = stockService;
    }

    @McpTool(name = "getStockByProductId", description = "Get stock information for a product by its ID")
    public Stock getStockByProductId(long productId) {
        return stockService.getStockByProductId(productId);
    }
}
