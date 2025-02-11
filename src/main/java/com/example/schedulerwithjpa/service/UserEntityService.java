package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.entity.UserEntity;
import com.example.schedulerwithjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEntityService {

    private final UserRepository userRepository;

    public List<UserEntity> findAllUserEntity() {
        return userRepository.findAll();
    }

    public UserEntity findUserEntityByUserIdOrElseThrow(Long userId) {
        return userRepository.findByIdOrElseThrow(userId);
    }

    public UserEntity findUserEntityByEmailAndPassword(String email, Long password) {
        return userRepository.findByEmailAndPasswordOrElseThrow(email, password);
    }
}
