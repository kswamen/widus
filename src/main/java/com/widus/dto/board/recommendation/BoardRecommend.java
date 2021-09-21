package com.widus.dto.board.recommendation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.widus.dto.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@IdClass(RecommendId.class)
public class BoardRecommend extends BaseTimeEntity {
	@Id
	private int boardId;
	
	@Id
	private String userEmail;
	
	@Column(columnDefinition = "int default 1")
	private Integer recommended;
	
	@Builder
	public BoardRecommend(int boardId, String userEmail, int recommended) {
		this.boardId = boardId;
		this.userEmail = userEmail;
		this.recommended = recommended;
	}
	
	// 1이면 추천됨, 0이면 추천 안됨
	public void toggleRecommend( ) {
		if (this.recommended == 1) {
			this.recommended = 0;
		}
		else if (this.recommended == 0) {
			this.recommended = 1;
		}
	}
}
