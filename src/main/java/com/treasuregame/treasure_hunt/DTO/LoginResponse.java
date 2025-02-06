package com.treasuregame.treasure_hunt.DTO;

import java.lang.*;


public class LoginResponse {
    private String token;

    private long expiresIn;

    public void setToken(String token) {
        this.token = token;
    }
    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getToken() {
        return token;
    }
}