package com.kemisshop.catalogservice.domain;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    KEMIS("kemis"),
    MEKENET("mekenet"),
    TIGRE("kelay-yemilebes"),
    WOLO ("ye wollo kemis"),
    RAYA("Ye raya kemis");


    private static Map<String, Category> storageByLabel = new HashMap<>();

    static {
        for (Category cat: values()) {
            storageByLabel.put(cat.label, cat);
        }
    }
    private String label;

    Category(String label) {
        this.label = label;
    }

    public static Category findByLabel(String label) {
        return storageByLabel.get(label);
    }

}
