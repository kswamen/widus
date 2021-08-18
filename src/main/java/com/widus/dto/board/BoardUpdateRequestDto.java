package com.widus.dto.board;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data 이때 성공했음
@Data
@NoArgsConstructor
public class BoardUpdateRequestDto {
	private String title;
	private String content;
	private BoardRole role;
	private String thumbnail;

	@Builder
	public BoardUpdateRequestDto(String title, String content, String writer, BoardRole role, String thumbnail, String email) {
		this.title = title;
		this.content = content;
		this.role = role;
		this.thumbnail = thumbnail;
	}
	
	public Board toEntity() {
		return Board.builder()
				.title(title)
				.content(content)
				.role(role)
				.thumbnail(thumbnail)
				.build();
	}
}
