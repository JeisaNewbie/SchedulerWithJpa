package com.example.schedulerwithjpa.repository;

import com.example.schedulerwithjpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    default UserEntity findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }

    default UserEntity findByEmailAndPasswordOrElseThrow(String email, Long password) {
        return findByEmailAndPassword(email, password).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }

    Optional<UserEntity> findByEmailAndPassword(String email, Long password);

}
