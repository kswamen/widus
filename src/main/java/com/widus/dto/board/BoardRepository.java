package com.widus.dto.board;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	@Query(value = "SELECT b FROM Board b WHERE b.role = :role and b.deleted = 'N' order by create_date desc, id")
	Page<Board> findByRole(@Param("role")BoardRole role, Pageable pageable);
	
	@Query(value = "SELECT COUNT(*) FROM Board b WHERE b.role = :role and b.deleted = 'N'")
	int getRecordCount(@Param("role") BoardRole role);
}
