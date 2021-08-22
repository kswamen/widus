package com.widus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.widus.dto.comment.Comment;
import com.widus.dto.comment.CommentRepository;

@SpringBootTest
public class CommentTest {
	@Autowired
	private CommentRepository commentRepository;

	
	@Test
	void insertComment() {
		// Error! null property
//		commentRepository.save(Comment.builder()
//				.content("content")
//				.build());
		
		commentRepository.save(Comment.builder()
				.nested(135L)
				.picture("asdfasdfasf")
				.content("content")
				.writer("김석원")
				.email("kswamen@naver.com")
				.boardId(12L)
				.build());
	}
}
