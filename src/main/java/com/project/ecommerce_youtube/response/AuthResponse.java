package com.project.ecommerce_youtube.response;

public class AuthResponse {
    private String jwt;
    private String msg;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AuthResponse(String jwt, String msg) {
        this.jwt = jwt;
        this.msg = msg;
    }

    public AuthResponse () {

    }
}
