package com.widus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.widus.dto.comment.Comment;
import com.widus.dto.comment.CommentRepository;
import com.widus.dto.comment.CommentSaveRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepository;
	
	@Transactional
	public Comment saveComment(CommentSaveRequestDto comment) {
		return commentRepository.save(comment.toEntity());
	}
	
	@Transactional
	public List<Comment> getCommentList(Long boardId) {
		return commentRepository.findByBoardId(boardId);
	}
}
