package com.numinolabs.PNCitizenAPI.Exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> HandleException(Exception ex, WebRequest request){
        System.out.println("getLocalizedMessage :" + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> HandleNullPointerException(Exception ex, WebRequest request){
        System.out.println("getLocalizedMessage :" + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), new HttpHeaders(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<?> HandleMethodArgumentNotValidException(DataIntegrityViolationException ex, WebRequest request){
        System.out.println("getLocalizedMessage :" + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }
}
