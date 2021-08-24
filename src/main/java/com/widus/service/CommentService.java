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
	public Comment saveComment(CommentSaveRequestDto commentSaveRequestDto) {
		if (commentSaveRequestDto.getId() == 0) {
			return commentRepository.save(commentSaveRequestDto.toEntity());
		}
		
		else {
			Comment comment = commentRepository.findById(commentSaveRequestDto.getId()).get();
			comment.commentUpdate(
					commentSaveRequestDto.getContent(),
					commentSaveRequestDto.getPicture());
			return commentRepository.save(comment);
		}
	}
	
	@Transactional
	public List<Comment> getCommentList(Long boardId) {
		return commentRepository.findByBoardId(boardId);
	}
	
	@Transactional
	public List<Comment> getNestedCommentList(Long boardId) {
		return commentRepository.findByBoardIdNested(boardId);
	}
	
	@Transactional
	public Comment deleteComment(Long commentId){
		Comment comment = commentRepository.findById(commentId).get();
		comment.commentDelete();
		
		return commentRepository.save(comment);
	}
}
