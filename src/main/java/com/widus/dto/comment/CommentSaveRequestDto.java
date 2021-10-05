package com.widus.dto.comment;

import com.widus.dto.board.Board;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentSaveRequestDto {
	private Long id;
	private String content;
	private String writer;
	private String email;
	private String picture;
	private String deleted;
	private Long nested;
	private Board board;
	private Long boardId;
	
	public Comment toEntity() {
		return Comment.builder()
				.id(id)
				.content(content)
				.writer(writer)
				.email(email)
				.picture(picture)
				.deleted(deleted)
				.nested(nested)
				.board(board)
				.build();
	}
}
