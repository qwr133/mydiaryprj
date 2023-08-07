package com.mydiary.mydiaryprj.userapi.service;

import com.mydiary.mydiaryprj.common.exception.ExceptionStatus;
import com.mydiary.mydiaryprj.common.exception.MyDiaryException;
import com.mydiary.mydiaryprj.userapi.dto.SignUpDTO;
import com.mydiary.mydiaryprj.userapi.entity.User;
import com.mydiary.mydiaryprj.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

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
                .password(request.getPassword())
                .nickname(request.getNickname())
                .phoneNumber(request.getPhoneNumber())
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

    public void duplicationCheckPhoneNumber(final String phoneNumber){
        if(userRepository.findByPhoneNumber(phoneNumber).isPresent()){
            throw new MyDiaryException(ExceptionStatus.DUPLICATION_PHONENUMBER);
        }
    }
}
