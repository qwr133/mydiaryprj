package com.mydiary.mydiaryprj.userapi.api;


import com.mydiary.mydiaryprj.common.domain.LoginUser;
import com.mydiary.mydiaryprj.userapi.dto.MyInfoDTO;
import com.mydiary.mydiaryprj.userapi.dto.SignInDTO;
import com.mydiary.mydiaryprj.userapi.dto.SignUpDTO;
import com.mydiary.mydiaryprj.userapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입")
    public HttpEntity<Void> signUp(@RequestBody @Valid SignUpDTO.Request request) {
        userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인")
    public HttpEntity<SignInDTO.Response> singIn(@RequestBody @Valid SignInDTO.Request request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(request));
    }

    @GetMapping("/my-info")
    @Operation(summary = "내정보조회")
    public HttpEntity<MyInfoDTO.Response> getMyInfo(LoginUser loginUser, String a, String b) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getMyInfo(loginUser));

    }

}
