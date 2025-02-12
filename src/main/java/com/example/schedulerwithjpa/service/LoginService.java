package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.dto.response.CreateUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;

    public CreateUserResponseDto saveUser(String username, String email, Long password) {
        return userService.saveUser(username, email, password);
    }

    public CreateUserResponseDto findUserByEmailAndPassword(String email, Long password) {
        return userService.findUserByEmailAndPassword(email, password);
    }
}
