package com.auth.authorizationserver.Exceptions;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    Map<String,String> errorMap= new HashMap<>();
    ProblemDetail errorMessage=null;

    @ExceptionHandler(UserExistException.class)
    public  ProblemDetail handleUserExistException(UserExistException ex){


        errorMessage=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
        errorMessage.setProperty("errorMessage",ex.getMessage());
return  errorMessage;

    }



    @ExceptionHandler(BadCredentialsException.class)

    public ProblemDetail handleSecurityException(BadCredentialsException ex){


        errorMessage=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
        errorMessage.setProperty("errorMessage","invalid user credentials");

        return  errorMessage;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ProblemDetail handleInvalidInputs(MethodArgumentNotValidException ex){


        errorMessage=ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        ex.getBindingResult()
                        .getFieldErrors()
                .forEach(err->{
                    errorMap.put(err.getField(),err.getDefaultMessage());
                });
        errorMessage.setProperty("errorMessage",errorMap);
        return  errorMessage;


    }


    @ExceptionHandler(ExpiredJwtException.class)
    public  ProblemDetail tokenExpired(ExpiredJwtException ex){

        errorMessage=ProblemDetail.forStatus(HttpStatus.FORBIDDEN);

        errorMap.put("errorMessage", ex.getMessage());

        errorMessage.setProperty("errorMessage",errorMap);
        return  errorMessage;

    }



}
