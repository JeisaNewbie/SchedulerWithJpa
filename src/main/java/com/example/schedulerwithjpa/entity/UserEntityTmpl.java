package com.example.schedulerwithjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityTmpl extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long password;

    @Column(nullable = false, unique = true)
    private String email;

    public void updateUser(String username, Long newPassword) {
        this.username = username;
        this.password = newPassword;
    }

    @OneToMany(mappedBy = "to_do")
    private List<ToDoEntity> toDoEntities;

}
