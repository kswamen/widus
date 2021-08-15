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

	@Column(nullable=false)
	private String content;

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
	
	@Column(columnDefinition = "int default 0")
	private Integer recommend;
	
	@Column
	private String thumbnail;

	@Builder
	public Board(String title, String content, String email, BoardRole role, String deleted, String thumbnail) {
		this.title = title;
		this.content = content;
		this.email = email;
		this.role = role;
		this.deleted = deleted;
		this.thumbnail = thumbnail;
	}
	
	public String getRoleKey() {
		return this.role.getKey();
	}
}
