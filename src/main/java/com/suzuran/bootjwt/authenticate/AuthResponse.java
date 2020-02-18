package com.suzuran.bootjwt.authenticate;

/**
 * @author sujan.koju.sastra@gmail.com 18/02/20
 */
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
