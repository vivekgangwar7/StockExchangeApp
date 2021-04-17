package com.navi.stockexchangeapp.module.companyorderbook;

import com.navi.stockexchangeapp.enums.CompanyOrderType;
import com.navi.stockexchangeapp.enums.FileInputType;
import com.navi.stockexchangeapp.module.companystock.CompanyStock;

public class CompanyOrderBuilder {
    public static CompanyOrder placeOrder(CompanyStock companyStock, String[] transactionInput) throws Exception {
        Long orderId = Long.parseLong(transactionInput[FileInputType.ID.ordinal()].split("#")[1]);
        CompanyOrderType stockOrderType =
                CompanyOrderType.getOrderType(transactionInput[FileInputType.ORDER_TYPE.ordinal()]);
        if(stockOrderType == null) {
            throw new Exception(String.format("Invalid order type: {}",
                    transactionInput[FileInputType.ORDER_TYPE.ordinal()]));
        }
     return CompanyOrder.builder().id(orderId)
             .timeStamp(transactionInput[FileInputType.TIME_STAMP.ordinal()])
             .stock(companyStock)
             .orderType(stockOrderType)
             .price(Double.parseDouble(transactionInput[FileInputType.PRICE.ordinal()]))
             .quantity(Integer.parseInt(transactionInput[FileInputType.QUANTITY.ordinal()])).build();
    }
}
