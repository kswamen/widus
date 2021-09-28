package com.widus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping(value = "/register.do", method = {RequestMethod.POST, RequestMethod.PATCH})
    public Comment commentRegister(@RequestBody final CommentSaveRequestDto comment) {
        return commentService.saveComment(comment);
    }
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public List<Comment> getCommentList(@RequestParam(value = "boardId", required = true)Long boardId) {
		return commentService.getCommentList(boardId);
	}
	
	@RequestMapping(value = "/nested/list.do", method = RequestMethod.GET)
	public List<Comment> getNestedCommentList(@RequestParam(value = "boardId", required = true)Long boardId) {
		return commentService.getNestedCommentList(boardId);
	}
	
	@DeleteMapping(value = "/delete.do/{id}")
	public Comment deleteComment(@PathVariable("id") final Long id) {
		System.out.println(id);
		return commentService.deleteComment(id);
	}
}
