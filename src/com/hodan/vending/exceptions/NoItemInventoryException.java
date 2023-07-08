package com.hodan.vending.exceptions;

public class NoItemInventoryException extends Exception {
    public NoItemInventoryException(String message) {
        super(message);
    }
}