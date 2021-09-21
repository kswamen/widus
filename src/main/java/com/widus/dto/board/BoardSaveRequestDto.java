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
	private String writer;
	private BoardRole role;
	private String thumbnail;
	private String email;

// 빌더 패턴이 필요한가?
//	@Builder
//	public BoardSaveRequestDto(String title, String content, String writer, BoardRole role, String thumbnail, String email) {
//		this.title = title;
//		this.content = content;
//		this.role = role;
//		this.writer = writer;
//		this.thumbnail = thumbnail;
//		this.email = email;
//	}
	
	public Board toEntity() {
		return Board.builder()
				.title(title)
				.content(content)
				.role(role)
				.writer(writer)
				.thumbnail(thumbnail)
				.email(email)
				.build();
	}
}
