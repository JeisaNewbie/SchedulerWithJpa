package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.dto.response.UserResponseDto;
import com.example.schedulerwithjpa.dto.response.UsersResponseDto;
import com.example.schedulerwithjpa.entity.User;
import com.example.schedulerwithjpa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UsersResponseDto> findAllUser() {
        return userRepository.findAll()
                .stream().map(UsersResponseDto::new)
                .toList();
    }

    public UserResponseDto findUserByUserId(Long id) {
        return new UserResponseDto(userRepository.findByIdOrElseThrow(id));
    }

    public UserResponseDto saveUser(String username, String email, Long password) {

        User user = User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        User savedUser = userRepository.findByEmailAndPassword(email, password)
                .orElseGet(() -> userRepository.save(user));

        return new UserResponseDto(savedUser);
    }

    @Transactional
    public void updateUser(String email, String username, Long oldPassword, Long newPassword) {

        User savedUser = userRepository.findByEmailAndPasswordOrElseThrow(email, oldPassword);

        savedUser.updateUser(username, newPassword);
    }

    public void deleteUser(String email, Long password) {
        User savedUser = userRepository.findByEmailAndPasswordOrElseThrow(email, password);

        userRepository.delete(savedUser);
    }
}
