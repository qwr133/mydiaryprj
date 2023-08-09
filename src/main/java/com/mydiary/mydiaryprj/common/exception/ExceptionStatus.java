package com.mydiary.mydiaryprj.common.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionStatus {

    DUPLICATION_EMAIL(400, "ABCDE", "중복되는 이메일입니다"),
    DUPLICATION_NICKNAME(400, "NICKN", "존재하는 닉네임 입니다"),
    DUPLICATION_PHONENUMBER(400, "KSDFE", "이미 존재하는 핸드폰 번호입니다"),

    CHECK_EMAIL_PASSWORD(401, "DSFWE", "이메일 또는 비밀번호를 다시 확인해주세요"),
    NON_SIGN_IN(401, "VSVDS", "비로그인"),

    INVALID_TOKEN(401, "WEGWQ", "유효하지 않은 토큰"),

    EXPIRED_TOKEN(401, "TRJWK", "만료된 토큰"),
    NOT_EXIST_USER(404, "FWEKN", "유저를 찾을 수 없음")

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
