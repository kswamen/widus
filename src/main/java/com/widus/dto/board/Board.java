package com.widus.dto.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.widus.dto.BaseTimeEntity;
import com.widus.dto.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private String title;

	@Column(nullable=false)
	private String content;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BoardRole role;

	@ManyToOne
	@JoinColumn(nullable=false, name = "user_email", referencedColumnName="email")
	private User email;

	@Column
	private String thumbnail;

	@Builder
	public Board(String title, String content, User email, BoardRole role, String thumbnail) {
		this.title = title;
		this.content = content;
		this.email = email;
		this.role = role;
		this.thumbnail = thumbnail;
	}
	
	public Board update(String title, String content, BoardRole role, String thumbnail) {
		this.title = title;
		this.content = content;
		this.role = role;
		this.thumbnail = thumbnail;
		
		return this;
	}
	
	public String getRoleKey() {
		return this.role.getKey();
	}
}
