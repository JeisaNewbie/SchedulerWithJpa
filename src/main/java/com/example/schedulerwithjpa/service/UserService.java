package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.dto.response.GetUserResponseDto;
import com.example.schedulerwithjpa.dto.response.GetUsersResponseDto;
import com.example.schedulerwithjpa.dto.response.CreateUserResponseDto;
import com.example.schedulerwithjpa.entity.UserEntity;
import com.example.schedulerwithjpa.repository.UserRepository;
import com.example.schedulerwithjpa.util.PasswordEncoder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserEntityService userEntityService;
    private final PasswordEncoder passwordEncoder;

    public List<GetUsersResponseDto> findAllUser() {
        return userRepository.findAll()
                .stream().map(GetUsersResponseDto::new)
                .toList();
    }

    public GetUserResponseDto findUserByUserId(Long id) {
        return new GetUserResponseDto(userRepository.findByIdOrElseThrow(id));
    }

    public CreateUserResponseDto findUserByEmailAndPassword(String email, Long password) {

        return new CreateUserResponseDto(userEntityService.validateUserOrElseThrow(email, password.toString()));
    }

    public CreateUserResponseDto saveUser(String username, String email, Long password) {

        String encoded = passwordEncoder.encode(password.toString());

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .email(email)
                .password(encoded)
                .build();

        UserEntity savedUserEntity = userRepository.findByEmailAndPassword(email, encoded)
                .orElseGet(() -> userRepository.save(userEntity));

        return new CreateUserResponseDto(savedUserEntity);
    }

    @Transactional
    public void updateUser(String email, String username, Long oldPassword, Long newPassword) {

        UserEntity savedUser = userEntityService.validateUserOrElseThrow(email, oldPassword.toString());

        String encodedNew = passwordEncoder.encode(newPassword.toString());

        savedUser.updateUser(username, encodedNew);
    }

    public void deleteUser(String email, Long password) {

        UserEntity savedUser = userEntityService.validateUserOrElseThrow(email, password.toString());

        userRepository.delete(savedUser);
    }



}
