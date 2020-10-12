package com.hsbc.abhishek.rates.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hsbc.abhishek.rates.model.ExceptionResponseModel;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

	public CustomizedExceptionHandling()
	{super();}
	
    @ExceptionHandler(RateNotFoundException.class)
    public ResponseEntity<Object> handleExceptions( RateNotFoundException exception, WebRequest webRequest) {
        
		ExceptionResponseModel response =new ExceptionResponseModel();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(exception.getMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
    }
    
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponseModel response =new ExceptionResponseModel();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("No Such Path Found, please check the request URL!!!");
        
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
    }
    
    
}