
package com.golovackii.FileStorage.model;

public enum Permission {
    
    USER_ADMIN("user:admin"),
    USER_CREATE("user:create"),
    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
    
    public String getPermission() {
        return permission;
    }
    
}
