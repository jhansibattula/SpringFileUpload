package com.spring.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundExeption extends RuntimeException  {

   // private static final long serialVersionUID = -7806029002430564887L;


    public UserNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundExeption(Throwable cause) {
        super(cause);
    }


    public UserNotFoundExeption() {
    }

    public   UserNotFoundExeption(String message) {
        super(message);
       // System.out.println("success");




    }
}
