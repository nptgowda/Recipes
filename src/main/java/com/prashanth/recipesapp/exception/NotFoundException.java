package com.prashanth.recipesapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String e){
        super(e);
    }
    public NotFoundException(String e,Throwable cause){
        super(e,cause);
    }
}
