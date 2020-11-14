package com.kemisshop.orderservice.domain;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {

    Shipped("Shipped"),
    Delivered("Delivered"),
    CheckedOut("Checked Out"),
    InProcess("In process"),
    Ordered("Ordered"),
    CancelledBySeller("Cancelled By Seller");

    private static Map<String, OrderStatus> statusMap =  new HashMap<>();

    static  {
        for(OrderStatus st : values()) {
            statusMap.put(st.label, st);
        }
    }
    private String label;

    OrderStatus(String label) {
        this.label = label;
    }

    public static OrderStatus findByLabel(String label) {
        return statusMap.get(label);
    }
}
