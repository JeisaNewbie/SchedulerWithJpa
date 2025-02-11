package com.example.schedulerwithjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "to_do")
@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ToDoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext", length = 200)
    private String toDo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "comment")
    private List<CommentEntityTmpl> comment;

    public void updateToDo(LocalDate date, String title, String toDo) {
        this.date = date;
        this.title = title;
        this.toDo = toDo;
    }
}
