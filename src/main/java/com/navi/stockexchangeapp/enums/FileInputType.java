package com.navi.stockexchangeapp.enums;

public enum FileInputType {

    ID("ID"),
    TIME_STAMP("TimeStamp"),
    COMPANY_NAME("CompanyName"),
    ORDER_TYPE("OrderType"),
    PRICE("Price"),
    QUANTITY("Quantity");
    private String fileInput;

    FileInputType(String fileInput) {
        this.fileInput = fileInput;
    }

}
