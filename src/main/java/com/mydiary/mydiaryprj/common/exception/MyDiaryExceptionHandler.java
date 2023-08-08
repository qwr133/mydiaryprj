package com.mydiary.mydiaryprj.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class MyDiaryExceptionHandler {

    @ExceptionHandler(MyDiaryException.class)
    public ResponseEntity<Error> myDiaryException(final MyDiaryException myDiaryException){
        return response(HttpStatus.valueOf(myDiaryException.httpStatusCode()),myDiaryException.message(),myDiaryException.code());
    }


    @ExceptionHandler(BindException.class)
    public ResponseEntity<Error> bindException(final BindException bindException) {
        List<String> errors = new ArrayList<>();
        bindException.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getField() + "(" + error.getDefaultMessage() + ")");
        });
        bindException.getGlobalErrors().forEach(error -> {
            errors.add(error.getObjectName() + "(" + error.getDefaultMessage() + ")");
        });

        return response(HttpStatus.BAD_REQUEST, errors.toString(), "ABCD");
    }

    private ResponseEntity<Error> response(final HttpStatus httpStatus, final String message, final String code){
        Error.Value error = Error.Value.builder()
                .code(code)
                .message(message)
                .build();
        return new ResponseEntity<>(Error.builder().error(error).build() ,httpStatus);
    }
}
