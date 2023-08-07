package com.mydiary.mydiaryprj.userapi.api;


import com.mydiary.mydiaryprj.userapi.dto.SignUpDTO;
import com.mydiary.mydiaryprj.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
