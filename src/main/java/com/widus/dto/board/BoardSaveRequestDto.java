package com.widus.dto.board;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data 이때 성공했음
@Data
@NoArgsConstructor
public class BoardSaveRequestDto {
	private String title;
	private String content;
	private BoardRole role;
	private String thumbnail;
	private String email;

	@Builder
	public BoardSaveRequestDto(String title, String content, BoardRole role, String thumbnail, String email) {
		this.title = title;
		this.content = content;
		this.role = role;
		this.thumbnail = thumbnail;
		this.email = email;
	}
	
	public Board toEntity() {
		return Board.builder()
				.title(title)
				.content(content)
				.role(role)
				.thumbnail(thumbnail)
				.email(email)
				.build();
	}
}
