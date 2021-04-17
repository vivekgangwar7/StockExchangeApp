package com.navi.stockexchangeapp.module.companystock;

import com.navi.stockexchangeapp.enums.CompanyOrderType;
import com.navi.stockexchangeapp.module.companyorderbook.CompanyOrder;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Comparator;

@AllArgsConstructor
public class CompanyStockComparator implements Comparator<CompanyOrder> {

    @NonNull private CompanyOrderType orderType;

    @Override
    public int compare(CompanyOrder order1, CompanyOrder order2) {
        if (orderType == CompanyOrderType.BUY) {
            if (order1.getPrice() < order2.getPrice()) {
                return 1;
            } else if (order1.getPrice() > order2.getPrice()) {
                return -1;
            }
            return 0;
        }
        if (orderType == CompanyOrderType.SELL) {
            if (order1.getPrice() > order2.getPrice()) {
                return 1;
            } else if (order1.getPrice() < order2.getPrice()) {
                return -1;
            }
        }
        return 0;
    }
}
