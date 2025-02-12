package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.dto.response.CreateCommentResponseDto;
import com.example.schedulerwithjpa.dto.response.GetAndUpdateCommentResponseDto;
import com.example.schedulerwithjpa.dto.response.GetCommentsResponseDto;
import com.example.schedulerwithjpa.entity.CommentEntity;
import com.example.schedulerwithjpa.entity.ToDoEntity;
import com.example.schedulerwithjpa.entity.UserEntity;
import com.example.schedulerwithjpa.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserEntityService userEntityService;
    private final ToDoEntityService toDoEntityService;
    private final CommentRepository commentRepository;

    public List<GetCommentsResponseDto> findAllComments() {
        return toDoEntityService.findAllToDoEntity()
                .stream()
                .map(GetCommentsResponseDto::new)
                .toList();
    }

    public GetAndUpdateCommentResponseDto findComment(Long id) {
        return new GetAndUpdateCommentResponseDto(commentRepository.findByIdOrElseThrow(id));
    }

    public CreateCommentResponseDto saveComment(Long userId, Long toDoId, String comment) {

        UserEntity userEntity = userEntityService.findUserEntityByUserIdOrElseThrow(userId);
        ToDoEntity toDoEntity = toDoEntityService.findToDoEntityByIdOrElseThrow(toDoId);

        CommentEntity commentEntity = CommentEntity.builder()
                .userEntity(userEntity)
                .toDoEntity(toDoEntity)
                .comment(comment)
                .build();

        return new CreateCommentResponseDto(commentRepository.save(commentEntity));
    }
}
