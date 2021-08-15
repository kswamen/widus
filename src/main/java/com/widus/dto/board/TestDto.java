package com.widus.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TestDto {
	private String id;
	private String pwd;
	
	@Builder
	public TestDto(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
}
