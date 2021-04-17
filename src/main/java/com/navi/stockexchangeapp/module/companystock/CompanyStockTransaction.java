package com.navi.stockexchangeapp.module.companystock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;

@Builder
@AllArgsConstructor
public class CompanyStockTransaction {

    @NonNull private Long buyStockOrderId;
    @NonNull private double price;
    @NonNull private int quantity;
    @NonNull private Long sellStockOrderId;

    public String toString() {
        return String.format("#%d %.2f %d #%d", buyStockOrderId, price, quantity, sellStockOrderId);
    }
}
