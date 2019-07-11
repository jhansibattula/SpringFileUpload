package com.spring.employee.controller;

public class SecurityResponce {


    private String error;

    public SecurityResponce() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public SecurityResponce(String error) {
        this.error = error;

    }
}
