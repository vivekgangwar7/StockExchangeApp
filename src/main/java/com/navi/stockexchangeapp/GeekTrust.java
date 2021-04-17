package com.navi.stockexchangeapp;

import com.navi.stockexchangeapp.parser.TransactionFileParser;
import com.navi.stockexchangeapp.module.companyorderbook.CompanyOrder;
import com.navi.stockexchangeapp.services.NaviStockExchange;
import com.navi.stockexchangeapp.module.companystock.CompanyStockTransaction;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GeekTrust Class
 *
 * It contains the logic of main function.
 * - Initialises stock exchange.
 * - Places order.
 * - returns the list of executed transactions.
 */
public class GeekTrust {

    public static void main(String[] args) {
        //File file = new File("/Users/vgangwa/Workspace/NaviSEApp/src/test/resources/testInput.txt");
        String filePath = args[0];
        File file = new File(filePath);
        List<String> completedTransactions = placeOrderNaviSE(file);
        completedTransactions.forEach(System.out::println);
        if (completedTransactions.size() == 0) {
            System.out.println("No transaction has been successfully placed. Place more Orders!!");
        }
    }

    public static List<String> placeOrderNaviSE(File file) {
        TransactionFileParser fileParser = null;
        try {
            fileParser = new TransactionFileParser();
            fileParser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        // Initialise stock exchange market
        NaviStockExchange stockExchange = new NaviStockExchange();

        // Start fetching orders
        List<CompanyOrder> stockExchangeOrderList = fileParser.getStockExchangeOrderList();

        // Match orders if they can be placed
        stockExchange.matchOrders(stockExchangeOrderList);

        // List of transactions which got successfully executed
        List<CompanyStockTransaction> executedTransactions = stockExchange.getExecutedTransactions();

        // Print list of transactions
        return executedTransactions.stream()
                .map(CompanyStockTransaction::toString)
                .collect(Collectors.toList());
    }
}
