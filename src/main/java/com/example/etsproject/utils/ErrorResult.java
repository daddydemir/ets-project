package com.example.etsproject.utils;

public class ErrorResult extends Result{

    public ErrorResult(){
        super(false);
    }

    public ErrorResult(String message){
        super(false, message);
    }
}
