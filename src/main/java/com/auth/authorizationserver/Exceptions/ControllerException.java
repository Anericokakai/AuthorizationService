package com.auth.authorizationserver.Exceptions;


import io.jsonwebtoken.MalformedJwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(value = {MalformedJwtException.class})
    public Map<String ,String > handled( MalformedJwtException ex){
        Map<String ,String > errorMap= new HashMap<>();

        errorMap.put("error",ex.getMessage());
        return  errorMap;
    }
}
