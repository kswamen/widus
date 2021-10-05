package com.widus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.widus.dto.board.Board;
import com.widus.dto.board.BoardRepository;
import com.widus.dto.comment.Comment;
import com.widus.dto.comment.CommentRepository;
import com.widus.dto.comment.CommentSaveRequestDto;
import com.widus.dto.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	
	@Transactional
	public Comment saveComment(CommentSaveRequestDto commentSaveRequestDto) {
		if (commentSaveRequestDto.getId() == 0) {
			commentSaveRequestDto.setBoard(boardRepository.findById(commentSaveRequestDto.getBoardId()).get());
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
	
	public List<Comment> getCommentList(Long boardId) {
		Board b = boardRepository.findById(boardId).get();
		User u = b.getUser();
		System.out.println(b.getUser().getEmail());
		return commentRepository.findByBoardId(b);
	}
	
	public List<Comment> getNestedCommentList(Long boardId) {
		return commentRepository.findByBoardIdNested(boardRepository.findById(boardId).get());
	}
	
	@Transactional
	public Comment deleteComment(Long commentId){
		Comment comment = commentRepository.findById(commentId).get();
		comment.commentDelete();
		
		return commentRepository.save(comment);
	}
}
