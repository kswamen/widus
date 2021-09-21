package com.widus;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.widus.dto.board.Board;
import com.widus.dto.board.BoardRepository;
import com.widus.dto.board.BoardRole;
import com.widus.dto.board.recommendation.BoardRecommend;
import com.widus.dto.board.recommendation.BoardRecommendRepository;
import com.widus.dto.board.recommendation.RecommendId;
import com.widus.dto.user.User;
import com.widus.dto.user.UserRepository;

@SpringBootTest
public class BoardTest {
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardRecommendRepository boardRecommendRepository;
	
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
		PageRequest pageRequest = PageRequest.of(0, 20);
		
		Page<Board> board = boardRepository.findByRole(BoardRole.FREE, pageRequest);
		List<Board> list = board.getContent();
	    list.forEach(b -> System.out.println(b.getId() + " " + b.getTitle()));
	}
	
	@Test
	void getRecordCount() {
		System.out.println(boardRepository.getRecordCount(BoardRole.FREE));
	}
	
	@Test
	void insertRecommend() {
		BoardRecommend b = BoardRecommend.builder().boardId(1)
				.userEmail("asdf@naver.com").build();
		boardRecommendRepository.save(b);
	}
	
	@Test
	void findRecommend() {
		RecommendId a = new RecommendId(2, "asdf@naver.com");
		Optional<BoardRecommend> b = boardRecommendRepository.findById(a);
		System.out.println(b.equals(Optional.empty()));
//		System.out.println(b.get().getBoardId());
//		System.out.println(b.get().getUserEmail());
	}
	
	@Test
	void toggleRecommend() {
		RecommendId a = new RecommendId(1, "asdf@naver.com");
		Optional<BoardRecommend> b = boardRecommendRepository.findById(a);
		
		BoardRecommend new_b = b.get();
		new_b.toggleRecommend();
		boardRecommendRepository.save(new_b);
	}
	
	@Test
	void countRecommend() {
		System.out.println(boardRecommendRepository.getRecommendCount(510));
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
