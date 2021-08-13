package com.widus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.widus.dto.board.Board;
import com.widus.dto.board.BoardRepository;
import com.widus.dto.board.BoardRole;
import com.widus.dto.user.User;
import com.widus.dto.user.UserRepository;

@SpringBootTest
public class BoardTest {
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void insertBoard() {
		User user = userRepository.findByEmail("kimseokwon95@gmail.com");
		
		final Board board = Board.builder()
				.email(user)
				.title("test title").content("test content")
				.role(BoardRole.FREE).build();
		boardRepository.save(board);
	}
	
	@Test
	void userPrintTest() {
		User user = userRepository.findByEmail("asdfasdf");
		System.out.println(user);
	}
}
