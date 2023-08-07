package com.mydiary.mydiaryprj.common.exception;


import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor
@AllArgsConstructor
public class Error {
    private Value error;


    @Getter @Setter
    @Builder @NoArgsConstructor
    @AllArgsConstructor
    public static class Value{
        private String message;
        private String code;
    }


}
