package com.mydiary.mydiaryprj.userapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@Builder //allarg랑 set
@AllArgsConstructor //builder,all 사용시 생성자가 만들어지지 않음으로 noarg를 붙여서 3개 사용함
@NoArgsConstructor
@Table(name = "user")
//@NoArgsConstructor

//@NoArgsConstructor - 디폴트생성자 - jpa 명세이므로
//@RequiredArgsConstructor
//@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 30, unique = true)
    private String nickname;

    @Column(nullable = false, length = 200, unique = true)
    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}