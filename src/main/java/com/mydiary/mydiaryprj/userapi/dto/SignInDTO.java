package com.mydiary.mydiaryprj.userapi.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

public class SignInDTO {

    @Getter
    public static class Request{

        @NotBlank(message = "이메일은 필수 입력값입니다")
        private String email;

        @NotBlank(message = "비밀번호도 필수로 입력해야합니다")
        private String password;

    }


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{

        private String jwt;

    }
}
