package com.kemisshop.catalogservice.exceptions;

public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }
}
