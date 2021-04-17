package com.navi.stockexchangeapp.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CompanyOrderType {

    BUY("buy"),
    SELL("sell");
    private String orderRequest;

    private static final Map<String, CompanyOrderType> stringNameToOrderTypeMap =
            Arrays.stream(CompanyOrderType.values()).collect(Collectors.toMap(CompanyOrderType::getName, Function.identity()));

    CompanyOrderType(String orderRequest) {
        this.orderRequest = orderRequest;
    }

    public String getName() {
        return orderRequest;
    }

    public static CompanyOrderType getOrderType(String orderType) {
        return stringNameToOrderTypeMap.get(orderType.toLowerCase());
    }
}
