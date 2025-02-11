package com.example.schedulerwithjpa.entity;

import jakarta.persistence.*;

@Entity
public class CommentEntityTmpl extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "to_do_id")
    private ToDoEntity toDo;
}
