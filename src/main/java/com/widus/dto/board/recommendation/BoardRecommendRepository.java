package com.widus.dto.board.recommendation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRecommendRepository extends JpaRepository<BoardRecommend, RecommendId> {
	@Query(value = "SELECT COUNT(*) FROM BoardRecommend b WHERE b.boardId = :boardId and b.recommended = 1")
	int getRecommendCount(@Param("boardId") int boardId);
}
