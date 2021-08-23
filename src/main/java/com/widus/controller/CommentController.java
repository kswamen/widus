package com.widus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.widus.dto.comment.Comment;
import com.widus.dto.comment.CommentSaveRequestDto;
import com.widus.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;
	
	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
    public Comment commentRegister(CommentSaveRequestDto comment) {
        return commentService.saveComment(comment);
    }
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public List<Comment> getCommentList(@RequestParam(value = "boardId", required = true)Long boardId) {
		return commentService.getCommentList(boardId);
	}
}
