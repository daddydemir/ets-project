package com.example.etsproject.core.business;

import com.example.etsproject.utils.Result;

public class BusinessRules {

    public static Result run(Result... logics){
        for(Result result:logics){
            if(!result.isSuccess()){
                return result;
            }
        }
        return null;
    }
}
