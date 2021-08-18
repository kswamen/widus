package com.widus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.widus.dto.board.Board;
import com.widus.dto.board.BoardRepository;
import com.widus.dto.board.BoardRole;
import com.widus.dto.board.BoardSaveRequestDto;
import com.widus.dto.board.BoardUpdateRequestDto;
import com.widus.dto.user.User;
import com.widus.dto.user.UserRepository;

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
	public Board saveBoard(BoardSaveRequestDto boardSaveRequestDto, Long id) {
		Board board = boardRepository.findById(id).get();
		board.boardUpdate(
				boardSaveRequestDto.getTitle(),
				boardSaveRequestDto.getContent(), 
				boardSaveRequestDto.getRole(), 
				boardSaveRequestDto.getThumbnail());
		return boardRepository.save(board);
	}

	@Transactional
	public List<Board> getBoardByParams(BoardRole role) {
		return boardRepository.findByParams(role);
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
