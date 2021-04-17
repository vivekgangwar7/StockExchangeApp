package com.navi.stockexchangeapp.module.companyorderbook;

import com.navi.stockexchangeapp.enums.CompanyOrderType;
import com.navi.stockexchangeapp.module.companystock.CompanyStock;
import com.navi.stockexchangeapp.module.companystock.CompanyStockComparator;
import com.navi.stockexchangeapp.module.companystock.CompanyStockTransaction;
import lombok.NonNull;

import java.util.*;

public class CompanyOrderBookModule {

    @NonNull private CompanyStock stock;

    private PriorityQueue<CompanyOrder> buyOrders = new PriorityQueue<CompanyOrder>(new CompanyStockComparator(CompanyOrderType.BUY));
    private PriorityQueue<CompanyOrder> sellOrders = new PriorityQueue<CompanyOrder>(new CompanyStockComparator(CompanyOrderType.SELL));

    public CompanyOrderBookModule(CompanyStock stock) {
        this.stock = stock;
    }

    public List<CompanyStockTransaction> addOrder(CompanyOrder order)  {
        if (order.getOrderType() == CompanyOrderType.BUY) {
            buyOrders.add(order);
        }else if (order.getOrderType() == CompanyOrderType.SELL) {
            sellOrders.add(order);
        }
        return match();
    }

    private List<CompanyStockTransaction> match() {
        List<CompanyStockTransaction> transactionList = new LinkedList<>();
        assert buyOrders.size() > 0 && sellOrders.size() > 0;
        assert buyOrders.peek()!= null && sellOrders.peek() != null;
        while ((buyOrders.peek() != null && sellOrders.peek() != null)
                && buyOrders.peek().getPrice() >= sellOrders.peek().getPrice()) {
            CompanyStockTransaction executedTransaction = checkTransactionExecutor(buyOrders.peek(), sellOrders.peek());
            transactionList.add(executedTransaction);
        }

        return transactionList;
    }

    private CompanyStockTransaction checkTransactionExecutor(@NonNull CompanyOrder buyOrder, @NonNull CompanyOrder sellOrder) {
        int buyOrderQuantity = buyOrder.getQuantity();
        int sellOrderQuantity = sellOrder.getQuantity();
        int quantitySold = Math.min(buyOrderQuantity, sellOrderQuantity);
        if (buyOrderQuantity > sellOrderQuantity) {
            CompanyOrder newOrderAfterSellingStocks = buyOrders.peek();
            newOrderAfterSellingStocks.setQuantity(Math.subtractExact(buyOrderQuantity, quantitySold));
            buyOrders.poll(); sellOrders.poll();
            buyOrders.add(newOrderAfterSellingStocks);

        } else if (buyOrderQuantity == sellOrderQuantity) {
            buyOrders.poll(); sellOrders.poll();
        } else {
            CompanyOrder newOrderAfterSellingStocks = sellOrders.peek();
            newOrderAfterSellingStocks.setQuantity(Math.subtractExact(sellOrderQuantity, quantitySold));
            buyOrders.poll(); sellOrders.poll();
            sellOrders.add(newOrderAfterSellingStocks);
        }
        return CompanyStockTransaction.builder()
                .buyStockOrderId(buyOrder.getId())
                .sellStockOrderId(sellOrder.getId())
                .quantity(quantitySold)
                .price(sellOrder.getPrice()).build();
    }
}
