package com.mydiary.mydiaryprj.userapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Setter @Getter
@RequiredArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
@Entity @Table(name = "tb_user")
public class User {

    /*
    userid

    userAccount
    userPassword
    userNickname
    userProfileImg
    userPhoneNumber
    userGender
    userBirth
    userSignDate

    userComment


     ver2. 등급설정하기
        */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 50, unique = true)
   private String userAccount;

    @Column(nullable = false, length = 1000)
    private String userPassword;

    @Column(nullable = false, length = 30, unique = true)
    private String userNickname;

    @Column(length = 50)
    @Builder.Default
    private String memberProfile = "profile.png";

    private String userProfileImg;

    @Column(nullable = false, length = 200, unique = true)
    private String userPhoneNumber;

    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private Gender Gender;

    @JsonFormat(pattern = "yyyy-HH-dd")
    private LocalDateTime memberBirth;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-HH-dd")
    private LocalDateTime userSignDate;

    @Column(nullable = false, length = 50)
    private String userComment;


}
