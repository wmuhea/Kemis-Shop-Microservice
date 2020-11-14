package com.kemisshop.accountservice.model;


import java.util.HashMap;
import java.util.Map;

public enum Type {

    ROLE_ADMIN("Admin"), ROLE_SELLER("Seller"), ROLE_BUYER("Buyer");

    private static Map<String, Type> byLabel = new HashMap<>();

    private String label;

    static {

        for (Type type : values()) {
            byLabel.put(type.label, type);
        }
    }

    Type(String label) {
        this.label = label;
    }

    public static Type findByLabel(String label) {
        return byLabel.get(label);
    }
}

