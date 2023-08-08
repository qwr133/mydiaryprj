package com.mydiary.mydiaryprj.userapi.api;


import com.mydiary.mydiaryprj.userapi.dto.MyInfoDTO;
import com.mydiary.mydiaryprj.userapi.dto.SignInDTO;
import com.mydiary.mydiaryprj.userapi.dto.SignUpDTO;
import com.mydiary.mydiaryprj.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public HttpEntity<Void> signUp(@RequestBody @Valid SignUpDTO.Request request){
        userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-in")
    public HttpEntity<SignInDTO.Response> singIn(@RequestBody @Valid SignInDTO.Request request){
        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(request));
    }

    @GetMapping("/my-info")
    public HttpEntity<MyInfoDTO.Response> getMyInfo(HttpServletRequest hsr){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getMyInfo(hsr));

    }

}
