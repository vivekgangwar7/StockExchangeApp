package com.navi.stockexchangeapp.exception;

/**
 * This exception is thrown when Size of the order list is
 * less than 6
 *
 */
public class InvalidSizeOrderException extends Exception {

    private static final long serialVersionUID = -8421899502983028089L;

    /**
     * Exception message depicting Invalid order size
     *
     * @param message the message
     */
    public InvalidSizeOrderException(String message) {
        super(message);
    }
}