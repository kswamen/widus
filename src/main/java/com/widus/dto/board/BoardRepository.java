package com.widus.dto.board;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.widus.dto.user.User;

public interface BoardRepository extends JpaRepository<Board, Long> {
	Optional<Board> findByEmail(String email);
	
	Optional<Board> findById(Long id);
}
