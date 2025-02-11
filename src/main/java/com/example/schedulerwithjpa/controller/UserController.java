package com.example.schedulerwithjpa.controller;

import com.example.schedulerwithjpa.dto.request.DeleteUserRequestDto;
import com.example.schedulerwithjpa.dto.request.SignUpRequestDto;
import com.example.schedulerwithjpa.dto.request.UpdateUserRequestDto;
import com.example.schedulerwithjpa.dto.response.UserResponseDto;
import com.example.schedulerwithjpa.dto.response.UsersResponseDto;
import com.example.schedulerwithjpa.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 모든 사용자 조회
    @GetMapping("/user")
    public ResponseEntity<List<UsersResponseDto>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUser());
    }

    // 단일 사용자 조회
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> findUserById(
            @Valid
            @Min(value = 1, message = "ID 값은 1 이상이어야 합니다.")
            @PathVariable Long id
            ) {
        return ResponseEntity.ok(userService.findUserByUserId(id));
    }

    // 사용자 생성
    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> saveUser(@Valid @RequestBody SignUpRequestDto dto) {
        return ResponseEntity.ok(userService.saveUser(
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword()
        ));
    }

    // 사용자 정보 수정
    @PatchMapping("/user")
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UpdateUserRequestDto dto) {

        userService.updateUser(
                dto.getEmail(),
                dto.getUsername(),
                dto.getOldPassword(),
                dto.getNewPassword()
        );

        return ResponseEntity.noContent().build();
    }

    // 사용자 삭제
    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteUser(@Valid @RequestBody DeleteUserRequestDto dto) {
        userService.deleteUser(dto.getEmail(), dto.getPassword());
        return ResponseEntity.noContent().build();
    }
}
