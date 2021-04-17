package com.navi.stockexchangeapp.util;

public final class Constants {

    private Constants() {}

    public static final class TransactionFileParserConstants {
        public static final int INPUT_SANITY = 6;
        public static final String SIZE_NOT_EQUAL_ERROR_MSG = "Size of input is less than 6";
        public static final String STRING_SPLITTER = "\\s+";
    }
}
