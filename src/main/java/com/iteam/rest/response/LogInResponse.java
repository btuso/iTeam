package com.iteam.rest.response;

public class LogInResponse {

    private String token;

    public LogInResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
