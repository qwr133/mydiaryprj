package com.mydiary.mydiaryprj.userapi.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class MyInfoDTO {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{

        private Long id;

        private String email;

        private String password;

        private String nickname;

        private String phoneNumber;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }
}
