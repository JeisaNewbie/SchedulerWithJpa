package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.dto.response.UserResponseDto;
import com.example.schedulerwithjpa.dto.response.UsersResponseDto;
import com.example.schedulerwithjpa.entity.UserEntity;
import com.example.schedulerwithjpa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> findAllUserEntity() {
        return userRepository.findAll();
    }

    public List<UsersResponseDto> findAllUser() {
        return userRepository.findAll()
                .stream().map(UsersResponseDto::new)
                .toList();
    }

    public UserResponseDto findUserByUserId(Long id) {
        return new UserResponseDto(userRepository.findByIdOrElseThrow(id));
    }

    public UserResponseDto saveUser(String username, String email, Long password) {

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        UserEntity savedUserEntity = userRepository.findByEmailAndPassword(email, password)
                .orElseGet(() -> userRepository.save(userEntity));

        return new UserResponseDto(savedUserEntity);
    }

    @Transactional
    public void updateUser(String email, String username, Long oldPassword, Long newPassword) {

        UserEntity savedUserEntity = userRepository.findByEmailAndPasswordOrElseThrow(email, oldPassword);

        savedUserEntity.updateUser(username, newPassword);
    }

    public void deleteUser(String email, Long password) {
        UserEntity savedUserEntity = userRepository.findByEmailAndPasswordOrElseThrow(email, password);

        userRepository.delete(savedUserEntity);
    }
}
