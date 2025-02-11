package com.example.schedulerwithjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "comment")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "to_do_id")
    private ToDoEntity toDo;
}
