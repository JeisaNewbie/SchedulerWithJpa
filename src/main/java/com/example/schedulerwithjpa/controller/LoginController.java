package com.example.schedulerwithjpa.controller;

import com.example.schedulerwithjpa.dto.request.LoginRequestDto;
import com.example.schedulerwithjpa.dto.request.SignUpRequestDto;
import com.example.schedulerwithjpa.dto.response.CreateUserResponseDto;
import com.example.schedulerwithjpa.dto.response.GetUserResponseDto;
import com.example.schedulerwithjpa.service.LoginService;
import com.example.schedulerwithjpa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
* Login 혹은 Signup 시 Session 에서 Key 를 발급
*/

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<Void> login(
            HttpServletRequest request,
            @RequestBody LoginRequestDto dto
    ) {

        CreateUserResponseDto user = loginService.findUserByEmailAndPassword(
                dto.getEmail(),
                dto.getPassword()
        );

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponseDto> signup(@RequestBody SignUpRequestDto dto) {

        return ResponseEntity.ok(loginService.saveUser(
                dto.getEmail(),
                dto.getUsername(),
                dto.getPassword()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok().build();
    }
}
