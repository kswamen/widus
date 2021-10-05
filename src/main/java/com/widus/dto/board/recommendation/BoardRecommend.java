package com.widus.dto.board.recommendation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.widus.dto.BaseTimeEntity;
import com.widus.dto.board.Board;
import com.widus.dto.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@IdClass(RecommendId.class)
public class BoardRecommend extends BaseTimeEntity {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board boardId;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_email")
	private User userEmail;
	
	@Column(columnDefinition = "int default 1")
	private Integer recommended;
	
	@Builder
	public BoardRecommend(Board boardId, User userEmail, int recommended) {
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
