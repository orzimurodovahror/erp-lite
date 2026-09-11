package com.example.erplite.exp;

public class BarcodeAlreadyExistsException extends RuntimeException {

    public BarcodeAlreadyExistsException(String message) {
        super(message);
    }

}