package com.auth.authorizationserver.Exceptions;


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

    @ExceptionHandler(UserExistException.class)
    public  ProblemDetail handleUserExistException(UserExistException ex){

        ProblemDetail errorMap=null;

        errorMap=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
        errorMap.setProperty("errorMessage",ex.getMessage());
return  errorMap;

    }



    @ExceptionHandler(BadCredentialsException.class)

    public ProblemDetail handleSecurityException(BadCredentialsException ex){
        ProblemDetail errorMap=null;

        errorMap=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
        errorMap.setProperty("errorMessage","invalid user credentials");

        return  errorMap;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ProblemDetail handleInvalidInputs(MethodArgumentNotValidException ex){

        Map<String,String> errorMap= new HashMap<>();
        ProblemDetail errorMessage=null;

        errorMessage=ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        ex.getBindingResult()
                        .getFieldErrors()
                .forEach(err->{
                    errorMap.put(err.getField(),err.getDefaultMessage());
                });
        errorMessage.setProperty("errorMessage",errorMap);
        return  errorMessage;





    }

}
