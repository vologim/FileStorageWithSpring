
package com.golovackii.FileStorage.model;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    
    ADMIN(Set.of(
            Permission.USER_ADMIN,
            Permission.USER_CREATE, 
            Permission.USER_READ, 
            Permission.USER_UPDATE, 
            Permission.USER_DELETE)),
    
    MODERATOR(Set.of(
            Permission.USER_CREATE, 
            Permission.USER_READ, 
            Permission.USER_UPDATE, 
            Permission.USER_DELETE)),
    
    USER(Set.of(
            Permission.USER_READ));
    
    private final Set<Permission> permissions;

    private Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
    
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
    
}
