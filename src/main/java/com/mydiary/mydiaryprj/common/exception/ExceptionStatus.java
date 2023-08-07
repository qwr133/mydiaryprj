package com.mydiary.mydiaryprj.common.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionStatus {

    DUPLICATION_EMAIL(400, "ABCDE", "중복되는 이메일입니다"),
    DUPLICATION_NICKNAME(400, "NICKN", "존재하는 닉네임 입니다")


    ;

    private int httpStatusCode;
    private String code;
    private String message;

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
