package com.widus;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.widus.dto.board.Board;
import com.widus.dto.board.BoardRepository;
import com.widus.dto.board.BoardRole;
import com.widus.dto.board.BoardSaveRequestDto;
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
				.email("kswamen@naver.com")
				.title("test title")
				.content("test content")
				.role(BoardRole.FREE).build();
		boardRepository.save(board);
	}
	
	@Test
	void getBoardList() {
		Collection<Board> board = boardRepository.findByParams(BoardRole.FREE);
		Board[] arr = board.toArray(new Board[board.size()]);
		System.out.println(board.size());
		for(int i = 0; i < board.size(); i++) {
			System.out.println(arr[i].getTitle());
		}
	}
	
	@Test
	void updateBoard() {
		Board board = boardRepository.findById(12L).get();
		
		board.boardUpdate("123", "123", BoardRole.FREE, "123");
		boardRepository.save(board);
//		BoardSaveRequestDto dto = BoardSaveRequestDto.builder()
//				.title("hello there")
//				.content("asdfasdfasdf")
//				.role(BoardRole.SECRET)
//				.thumbnail("asdfasdfffff")
//				.email("asdfasf@naver.com")
//				.build();
//		
//		boardRepository.save(dto.toEntity());
	}
	
	@Test
	@Transactional
	void findOneBoard() {
		Long id = 1L;
		User user = userRepository.findByEmail("kimseokwon95@gmail.com");
		System.out.println(boardRepository.findByEmail("kimseokwon95@gmail.com"));
		System.out.println(boardRepository.findById(id));
	}
	
}
