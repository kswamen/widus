package com.widus.dto.comment;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentSaveRequestDto {
	private String content;
	private String writer;
	private String email;
	private String picture;
	private String deleted;
	private Long nested;
	private Long boardId;
	
	@Builder
	public CommentSaveRequestDto(String content, String writer, String email, String picture, String deleted, Long nested, Long boardId) {
		this.content = content;
		this.writer = writer;
		this.email = email;
		this.picture = picture;
		this.deleted = deleted;
		this.nested = nested;
		this.boardId = boardId;
	}
	
	public Comment toEntity() {
		return Comment.builder()
				.content(content)
				.writer(writer)
				.email(email)
				.picture(picture)
				.deleted(deleted)
				.nested(nested)
				.boardId(boardId)
				.build();
	}
}
