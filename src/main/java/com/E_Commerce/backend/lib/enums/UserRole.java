package com.E_Commerce.backend.lib.enums;

public enum UserRole {
    SELLER(1),
    BUYER(2),
    ADMIN(3);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public static UserRole fromInt(int i) {
        for (UserRole role : UserRole.values()) {
            if (role.getValue() == i) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + i);
    }

    public int getValue() {
        return value;
    }
}
