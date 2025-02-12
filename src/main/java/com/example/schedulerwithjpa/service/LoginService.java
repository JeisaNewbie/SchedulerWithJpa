package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.dto.response.CreateUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;

    public CreateUserResponseDto saveUser(String email, String username, Long password) {
        return userService.saveUser(email, username, password);
    }

    public CreateUserResponseDto findUserByEmailAndPassword(String email, Long password) {
//        return userService.findUserByEmailAndPasswordOrElseThrow(email, password);
        return null;
    }
}
