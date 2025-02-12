package com.example.schedulerwithjpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "comment")
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "to_do_id")
    private ToDoEntity toDoEntity;

    public void updateComment(String comment) {
        this.comment = comment;
    }
}
