package com.widus.dto.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardRole {
	FREE("FREE", "자유 게시판"),
	SECRET("SECRET", "비밀 게시판"),
	SCHOOLLIFE("SCHOOLLIFE", "학교생활 게시판");
	
	private final String key;
	private final String value;
}
