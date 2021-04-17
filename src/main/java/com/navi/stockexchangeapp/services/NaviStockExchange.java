package com.navi.stockexchangeapp.services;

import com.navi.stockexchangeapp.module.companyorderbook.CompanyOrderBookModule;
import com.navi.stockexchangeapp.module.companystock.CompanyStock;
import com.navi.stockexchangeapp.module.companystock.CompanyStockTransaction;
import com.navi.stockexchangeapp.module.companyorderbook.CompanyOrder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NaviStockExchange {

    private List<CompanyStockTransaction> companyStockTransactions = new LinkedList<>();
    private Map<CompanyStock, CompanyOrderBookModule> companyStockOrderManager = new HashMap<>();

    public void placeOrder(CompanyOrder companyOrder) {
        CompanyStock companyStock = companyOrder.getStock();
        if(!companyStockOrderManager.containsKey(companyStock)) {
            companyStockOrderManager.put(companyStock, new CompanyOrderBookModule(companyStock));
        }

        List<CompanyStockTransaction> transactionsExecuted =
                companyStockOrderManager.get(companyStock).addOrder(companyOrder);
        companyStockTransactions.addAll(transactionsExecuted);
    }

    public void matchOrders(List<CompanyOrder> orders) {
        orders.forEach(this::placeOrder);
    }

    public List<CompanyStockTransaction> getExecutedTransactions() {
        return companyStockTransactions;
    }
}
