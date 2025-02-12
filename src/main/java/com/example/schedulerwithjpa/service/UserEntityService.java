package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.entity.ToDoEntity;
import com.example.schedulerwithjpa.entity.UserEntity;
import com.example.schedulerwithjpa.repository.UserRepository;
import com.example.schedulerwithjpa.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEntityService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> findAllUserEntity() {
        return userRepository.findAll();
    }

    public UserEntity findUserEntityByUserIdOrElseThrow(Long userId) {
        return userRepository.findByIdOrElseThrow(userId);
    }

    public UserEntity validateUserOrElseThrow(String email, String password) {

        UserEntity user = userRepository.findByEmailOrElseThrow(email);

        passwordEncoder.verify(password, user.getPassword());

        return user;
    }
}
