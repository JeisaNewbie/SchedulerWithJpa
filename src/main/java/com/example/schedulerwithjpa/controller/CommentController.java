package com.example.schedulerwithjpa.controller;

import com.example.schedulerwithjpa.dto.request.CommentRequestDto;
import com.example.schedulerwithjpa.dto.request.DeleteCommentRequestDto;
import com.example.schedulerwithjpa.dto.request.UpdateCommentRequestDto;
import com.example.schedulerwithjpa.dto.response.CreateCommentResponseDto;
import com.example.schedulerwithjpa.dto.response.GetAndUpdateCommentResponseDto;
import com.example.schedulerwithjpa.dto.response.GetCommentsResponseDto;
import com.example.schedulerwithjpa.dto.response.UpdateCommentResponseDto;
import com.example.schedulerwithjpa.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment")
    public ResponseEntity<List<GetCommentsResponseDto>> findAllComments() {
        return ResponseEntity.ok(commentService.findAllComments());
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<GetAndUpdateCommentResponseDto> findCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findComment(id));
    }

    @PostMapping("/comment")
    public ResponseEntity<CreateCommentResponseDto> saveComment(@RequestBody CommentRequestDto dto) {
        return ResponseEntity.ok(commentService.saveComment(dto.getUserId(), dto.getToDoId(), dto.getComment()));
    }

    @PatchMapping("/comment")
    public ResponseEntity<UpdateCommentResponseDto> updateComment(@RequestBody UpdateCommentRequestDto dto) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Void> deleteComment(@RequestBody DeleteCommentRequestDto dto) {
        return ResponseEntity.noContent().build();
    }
}
