package com.mydiary.mydiaryprj.common.exception;

public class MyDiaryException extends RuntimeException {
    private int httpStatusCode;
    private String code;
    private String message;


    public MyDiaryException(ExceptionStatus exceptionStatus) {
        super(exceptionStatus.name()); //name : DUPLICATION_EMAIL
        this.httpStatusCode= exceptionStatus.httpStatusCode();
        this.code=exceptionStatus.code();
        this.message=exceptionStatus.message();

    }

    public int httpStatusCode(){
        return this.httpStatusCode;
    }
    public String code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }
}
