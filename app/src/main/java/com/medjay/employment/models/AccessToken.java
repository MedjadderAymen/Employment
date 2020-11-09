package com.medjay.employment.models;

import com.squareup.moshi.Json;

public class AccessToken {

    @Json(name="token")
    private String token;

    @Json(name="email")
    private String email;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
