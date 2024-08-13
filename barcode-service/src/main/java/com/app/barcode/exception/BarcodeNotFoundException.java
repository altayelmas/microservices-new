package com.app.barcode.exception;

public class BarcodeNotFoundException extends RuntimeException {

    public BarcodeNotFoundException(String message) {
        super(message);
    }
}
