package com.navi.stockexchangeapp.module.companyorderbook;

import com.navi.stockexchangeapp.enums.CompanyOrderType;
import com.navi.stockexchangeapp.module.companystock.CompanyStock;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CompanyOrder {

    @NonNull private Long id;
    @NonNull private String timeStamp;
    @NonNull private CompanyStock stock;
    @NonNull private CompanyOrderType orderType;
    @NonNull private double price;
    @NonNull private int quantity;
}
