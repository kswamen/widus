package com.widus.dto.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.widus.dto.board.Board;

public interface CommentRepository extends JpaRepository<Comment, Long>  {
	@Query(value = "SELECT c FROM Comment c WHERE c.deleted = 'N' and c.board = :boardId and nested = 0")
	List<Comment> findByBoardId(@Param("boardId")Board board);
	
	@Query(value = "SELECT c FROM Comment c WHERE c.deleted = 'N' and c.board = :boardId and nested != 0")
	List<Comment> findByBoardIdNested(@Param("boardId")Board board);
}
