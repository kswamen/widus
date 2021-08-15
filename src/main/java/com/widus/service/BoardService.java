package com.widus.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.widus.dto.board.Board;
import com.widus.dto.board.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	@Transactional
	public Optional<Board> getBoardById(long id) {
		return boardRepository.findById(id);
	}
	
	@Transactional
	public Board saveBoard(Board board) {
		return boardRepository.save(board);
	}
}
