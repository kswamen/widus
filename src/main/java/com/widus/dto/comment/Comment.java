package com.widus.dto.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import com.widus.dto.BaseTimeEntity;
import com.widus.dto.board.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
public class Comment extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, columnDefinition = "LONGTEXT")
	private String content;
	
	@Column(nullable=false)
	private String writer;
	
	@Column(nullable = false)
	private String email;
	
	@Column
	private String picture;
	
	@Column(columnDefinition = "varchar(255) default 'N'")
	private String deleted;
	
	@Column(columnDefinition = "bigint default 0")
	private Long nested;
	
	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;
	
	@Builder
	public Comment(Long id, String content, String writer, String email, String picture, String deleted, Long nested, Board board) {
		this.id = id;
		this.content = content;
		this.writer = writer;
		this.email = email;
		this.picture = picture;
		this.deleted = deleted;
		this.nested = nested;
		this.board = board;
	}
	
	public void commentUpdate(String content, String picture) {
		this.content = content;
		this.picture = picture;
	}
	
	public void commentDelete() {
		this.deleted = "Y";
	}
}
