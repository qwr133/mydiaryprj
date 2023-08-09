package com.mydiary.mydiaryprj.userapi.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class SignUpDTO {

    //    @Setter - 클라이언트 데이터는 수정을 하면 안되므로 setter 할 필요가 없음
            //Q. 내 정보 변경 시 setter가 필요하지 않나?
    @Getter
    public static class Request {

        @NotBlank(message = "이메일을 확인해주세요")
        private String email;

        @NotBlank(message = "비밀번호를 확인해주세요")
        private String password;

        @NotBlank(message = "닉네임을 확인해주세요")
        private String nickname;

        @NotBlank(message = "핸드폰번호를 확인해주세요")
        private String phoneNumber;
    }
}
