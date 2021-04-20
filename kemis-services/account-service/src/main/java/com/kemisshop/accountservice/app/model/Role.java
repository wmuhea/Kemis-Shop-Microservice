package com.kemisshop.accountservice.app.model;


import java.util.HashMap;
import java.util.Map;


/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */

public enum Role {

    ROLE_ADMIN("Admin"), ROLE_SELLER("Seller"), ROLE_BUYER("Buyer");

    private static Map<String, Role> byLabel = new HashMap<>();

    private String label;

    static {

        for (Role role : values()) {
            byLabel.put(role.label, role);
        }
    }

    Role(String label) {
        this.label = label;
    }

    public static Role findByLabel(String label) {
        return byLabel.get(label);
    }
}

