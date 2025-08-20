package com.example.fitHerWay.exception.custom;

public class InvalidPhoneFormat extends RuntimeException{
    public InvalidPhoneFormat(String message){
        super(message);
    }
}
