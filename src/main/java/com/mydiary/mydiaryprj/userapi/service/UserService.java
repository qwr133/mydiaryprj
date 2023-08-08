package com.mydiary.mydiaryprj.userapi.service;

import com.mydiary.mydiaryprj.common.AES;
import com.mydiary.mydiaryprj.common.JwtProvider;
import com.mydiary.mydiaryprj.common.MyDiaryBcrypt;
import com.mydiary.mydiaryprj.common.exception.ExceptionStatus;
import com.mydiary.mydiaryprj.common.exception.MyDiaryException;
import com.mydiary.mydiaryprj.userapi.dto.MyInfoDTO;
import com.mydiary.mydiaryprj.userapi.dto.SignInDTO;
import com.mydiary.mydiaryprj.userapi.dto.SignUpDTO;
import com.mydiary.mydiaryprj.userapi.entity.User;
import com.mydiary.mydiaryprj.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    @Value("${aes.secret}")
    private String aesSecret;

    @Value("${jwt.secret}")
    private String jwtSecret;


    private final UserRepository userRepository;

    public void signUp(final SignUpDTO.Request request) {

        //이메일 중복확인
        duplicationCheckEmail(request.getEmail());

        //닉네임 중복확인
        duplicationCheckNickname(request.getNickname());

        //핸드폰 번호 중복확인
        duplicationCheckPhoneNumber(request.getPhoneNumber());

        User user = User.builder()
                .email(request.getEmail())
                .password(MyDiaryBcrypt.hashpw(request.getPassword()))
                .nickname(request.getNickname())
                .phoneNumber(AES.encrypt(request.getPhoneNumber(), aesSecret))
                .build();

        userRepository.save(user);

        //test
//        if (true){
//            throw new MyDiaryException(ExceptionStatus.DUPLICATION_EMAIL);
//        }
    }

    public void duplicationCheckEmail(final String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new MyDiaryException(ExceptionStatus.DUPLICATION_EMAIL);
        }
    }

    public void duplicationCheckNickname(final String nickname) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new MyDiaryException(ExceptionStatus.DUPLICATION_NICKNAME);
        }
    }

    public void duplicationCheckPhoneNumber(final String phoneNumber) {
        if (userRepository.findByPhoneNumber(AES.encrypt(phoneNumber, aesSecret)).isPresent()) {
            throw new MyDiaryException(ExceptionStatus.DUPLICATION_PHONENUMBER);
        }
    }

    public SignInDTO.Response signIn(final SignInDTO.Request request) {

        User user=  userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MyDiaryException(ExceptionStatus.CHECK_EMAIL_PASSWORD));

        //비밀번호 확인(사용자 비번 / db 비번)
        validPassword(request.getPassword(), user.getPassword());

        String jwt = JwtProvider.make(JwtProvider.Recipe.builder().id(user.getId()).build(), jwtSecret);

        return  SignInDTO.Response.builder().jwt(jwt).build();
    }

    private void validPassword(final String password, final String hashedPassword){
        if (!MyDiaryBcrypt.checkpw(password, hashedPassword)){
            throw new MyDiaryException(ExceptionStatus.CHECK_EMAIL_PASSWORD);
        }
    }

    public MyInfoDTO.Response getMyInfo(final HttpServletRequest hsr) {
        String authorization = hsr.getHeader("Authorization");

        String BEARER = "Bearer ";

        String jwt =  authorization.substring(BEARER.length());




    }
}
