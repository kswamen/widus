package com.widus.dto.board.recommendation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardRecommendRequestDto {
	private int boardId;
	private String userEmail;
}
