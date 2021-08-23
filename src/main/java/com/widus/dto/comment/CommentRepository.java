package com.widus.dto.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>  {
	List<Comment> findByBoardId(Long boardId);
}
