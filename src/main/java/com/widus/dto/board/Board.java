package com.widus.dto.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.widus.dto.BaseTimeEntity;
import com.widus.dto.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
public class Board extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private String title;

	@Column(nullable=false, columnDefinition = "LONGTEXT")
	private String content;
	
	@Column(nullable=false)
	private String writer;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BoardRole role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_email")
	@JsonIgnore
	private User user;
	
	@Column(columnDefinition = "varchar(255) default 'N'")
	private String deleted;
	
	@Column
	private String thumbnail;
	
	@Column(columnDefinition = "int default 0")
	private int visit;

	@Builder
	public Board(String title, String content, String writer, User user, BoardRole role, String deleted, String thumbnail) {
		this.title = title;
		this.content = content;
		this.user = user;
		this.writer = writer;
		this.role = role;
		this.deleted = deleted;
		this.thumbnail = thumbnail;
	}
	
	public void boardUpdate(String title, String content, BoardRole role, String thumbnail) {
		this.title = title;
		this.content = content;
		this.role = role;
		this.thumbnail = thumbnail;
	}
	
	public void boardVisitPlus() {
		this.visit += 1;
	}
	
	public void boardDelete() {
		this.deleted = "Y";
	}
	
	public String getRoleKey() {
		return this.role.getKey();
	}
}
