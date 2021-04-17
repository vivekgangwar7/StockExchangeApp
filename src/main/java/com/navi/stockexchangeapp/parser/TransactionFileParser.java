package com.navi.stockexchangeapp.parser;

import com.navi.stockexchangeapp.enums.FileInputType;
import com.navi.stockexchangeapp.services.StockExchangeDatabase;
import com.navi.stockexchangeapp.module.companyorderbook.CompanyOrderBuilder;
import com.navi.stockexchangeapp.module.companyorderbook.CompanyOrder;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.navi.stockexchangeapp.module.companystock.CompanyStock;
import com.navi.stockexchangeapp.exception.InvalidSizeOrderException;
import com.navi.stockexchangeapp.util.Constants;

public class TransactionFileParser {

    private StockExchangeDatabase stockDatabase = new StockExchangeDatabase();
    private List<CompanyOrder> companyOrderList;

    public void parse(File inputFile) throws Exception {
        List<CompanyOrder> list = new LinkedList<>();
        List<String> inputLines = new FileParser().parse(inputFile);
        for (String inputLine : inputLines) {
            CompanyOrder order = parseLine(inputLine);
            list.add(order);
        }
        companyOrderList = list;
    }

    public List<CompanyOrder> getStockExchangeOrderList() {
        return companyOrderList;
    }

    private CompanyOrder parseLine(String orderInput) throws Exception {
        String[] transactionInput = orderInput.split(Constants.TransactionFileParserConstants.STRING_SPLITTER);
        if (transactionInput.length != Constants.TransactionFileParserConstants.INPUT_SANITY) {
            throw new InvalidSizeOrderException(Constants.TransactionFileParserConstants.SIZE_NOT_EQUAL_ERROR_MSG);
        }
        String stockName = transactionInput[FileInputType.COMPANY_NAME.ordinal()];
        stockDatabase.addStock(stockName);
        CompanyStock stock = stockDatabase.getStock(stockName);

        return CompanyOrderBuilder.placeOrder(stock, transactionInput);
    }

}
