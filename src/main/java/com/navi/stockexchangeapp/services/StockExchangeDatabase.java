package com.navi.stockexchangeapp.services;

import com.navi.stockexchangeapp.module.companystock.CompanyStock;

import java.util.HashMap;
import java.util.Map;

public class StockExchangeDatabase {

    private Map<String, CompanyStock> stockExchangeManager = new HashMap<>();

    public void addStock(String stockName) {
        if(!stockExchangeManager.containsKey(stockName)) {
            CompanyStock stock = new CompanyStock(stockName);
            stockExchangeManager.put(stockName, stock);
        }
    }

    public CompanyStock getStock(String stockName) {
        return stockExchangeManager.get(stockName);
    }
}
