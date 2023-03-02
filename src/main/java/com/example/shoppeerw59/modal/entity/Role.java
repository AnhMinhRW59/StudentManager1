package com.example.shoppeerw59.modal.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    SELLER,CUSTOMER,ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
