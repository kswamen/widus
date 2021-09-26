package com.widus.dto.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;

import com.widus.dto.BaseTimeEntity;

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

//	@ManyToOne
//	@JoinColumn(nullable=false, name = "user_email", referencedColumnName="email")
//	private User email;

	@Column(nullable = false)
	private String email;
	
	@Column(columnDefinition = "varchar(255) default 'N'")
	private String deleted;
	
	@Column
	private String thumbnail;
	
	@Column(columnDefinition = "int default 0")
	private String visit;

	@Builder
	public Board(String title, String content, String writer, String email, BoardRole role, String deleted, String thumbnail) {
		this.title = title;
		this.content = content;
		this.email = email;
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
	
	public void boardDelete() {
		this.deleted = "Y";
	}
	
	public String getRoleKey() {
		return this.role.getKey();
	}
}
