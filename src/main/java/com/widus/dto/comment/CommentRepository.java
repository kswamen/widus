package com.widus.dto.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long>  {
	@Query(value = "SELECT c FROM Comment c WHERE c.deleted = 'N' and c.boardId = :boardId")
	List<Comment> findByBoardId(@Param("boardId")Long boardId);
}
