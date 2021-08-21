package com.widus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.widus.dto.board.Board;
import com.widus.dto.board.BoardRepository;
import com.widus.dto.board.BoardRole;
import com.widus.dto.board.BoardSaveRequestDto;
import com.widus.dto.board.BoardUpdateRequestDto;
import com.widus.dto.user.User;
import com.widus.dto.user.UserRepository;
import com.widus.paging.Criteria;
import com.widus.paging.PagingDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	@Transactional
	public Optional<Board> getBoardById(long id) {
		return boardRepository.findById(id);
	}

	@Transactional
	public Board saveBoard(BoardSaveRequestDto board) {
		return boardRepository.save(board.toEntity());
	}
	
	@Transactional
	public Page<Board> findAll(PageRequest pageRequest) {
		return boardRepository.findAll(pageRequest);
	}
	
	@Transactional
	public Board saveBoard(BoardSaveRequestDto boardSaveRequestDto, Long id) {
		Board board = boardRepository.findById(id).get();
		board.boardUpdate(
				boardSaveRequestDto.getTitle(),
				boardSaveRequestDto.getContent(), 
				boardSaveRequestDto.getRole(), 
				boardSaveRequestDto.getThumbnail());
		return boardRepository.save(board);
	}

//	@Transactional
//	public Page<Board> getBoardByRole(BoardRole role, Pageable pageable) {
//		return boardRepository.findByRole(role, pageable);
//	}
	
	@Transactional
	public Page<Board> getBoardByRole(BoardRole role, Criteria criteria) {
		// JPA는 인덱스가 0부터 시작하기 때문에 1을 빼 준다.
		PageRequest pageRequest = PageRequest.of(criteria.getCurrentPageNo() - 1, criteria.getRecordsPerPage());
		//PageRequest pageRequest = PageRequest.of(0, 10);
//		System.out.println(criteria.getCurrentPageNo() + " " + criteria.getRecordsPerPage());
		Page<Board> pageBoard = boardRepository.findByRole(role, pageRequest);
		
//		List<Board> list = pageBoard.getContent();
//	    list.forEach(b -> System.out.println(b.getId() + " " + b.getTitle()));
		
		return pageBoard;
	}
	
	public PagingDto getPagingDto(BoardRole role, Criteria criteria) {
		int boardTotalCnt = boardRepository.getRecordCount(role);
		PagingDto pagingDto = new PagingDto(criteria);
		pagingDto.setTotalRecordCount(boardTotalCnt);
		
		return pagingDto;
	}
	
	@Transactional
	public User getWroteUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Transactional
	public Board updateBoard(BoardUpdateRequestDto boardUpdateRequestDto, Long id) {
		Board board = boardRepository.findById(id).get();
		board.boardUpdate(
				boardUpdateRequestDto.getTitle(),
				boardUpdateRequestDto.getContent(), 
				boardUpdateRequestDto.getRole(), 
				boardUpdateRequestDto.getThumbnail());
		return boardRepository.save(board);
	}
	
	@Transactional
	public Board deleteBoard(Long id) {
		Board board = boardRepository.findById(id).get();
		board.boardDelete();
		
		return boardRepository.save(board);
	}
}
