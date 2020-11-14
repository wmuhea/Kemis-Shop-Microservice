package com.kemisshop.orderservice.exceptions;

import java.util.NoSuchElementException;

public class ProductIsNotOrderedException  extends NoSuchElementException {
    @Override
    public String getMessage() {
        return "Product is not ordered, and it is safe to delete it";
    }
}
