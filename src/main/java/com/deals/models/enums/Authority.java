package com.deals.models.enums;

public enum Authority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    public final String role;

    private Authority(String role) {
        this.role = role;
    }

}
