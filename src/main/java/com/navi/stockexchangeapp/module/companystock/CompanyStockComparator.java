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
        int order1TimeStamp = companyOrderIntegerTime(order1.getTimeStamp());
        int order2TimeStamp = companyOrderIntegerTime(order2.getTimeStamp());
        if (orderType == CompanyOrderType.BUY) {
            if (order1.getPrice() < order2.getPrice()) {
                return 1;
            } else if (order1.getPrice() == order2.getPrice()) {
                if (order1TimeStamp > order2TimeStamp) {
                    return 1;
                } else if (order1TimeStamp == order2TimeStamp) {
                    return 0;
                }
            }
            return -1;
        }
        if (orderType == CompanyOrderType.SELL) {
            if (order1.getPrice() > order2.getPrice()) {
                return 1;
            } else if (order1.getPrice() == order2.getPrice()) {
                if (order1TimeStamp > order2TimeStamp) {
                    return 1;
                } else if (order1TimeStamp == order2TimeStamp) {
                    return 0;
                }
            }
        }
        return -1;
    }

    private int companyOrderIntegerTime(String timeStamp) {
        int h1 = timeStamp.charAt(0) - '0';
        int h2 = timeStamp.charAt(1) - '0';
        int m1 = timeStamp.charAt(3) - '0';
        int m2 = timeStamp.charAt(4) - '0';
        return (h1*10+h2)*60+(m1*10+m2);
    }
}
