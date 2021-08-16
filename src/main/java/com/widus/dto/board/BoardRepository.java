package com.widus.dto.board;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
	Optional<Board> findByEmail(String email);
	
	Optional<Board> findById(Long id);
	
	@Query(value = "SELECT b FROM Board b WHERE b.role = :role and b.deleted = 'N' order by create_date desc")
	List<Board> findByParams(@Param("role")BoardRole role);
}
