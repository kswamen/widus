package com.widus.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.widus.dto.board.BoardSaveRequestDto;

@RestController
@RequestMapping("boardTest")
public class BoardRestController {

	@PostMapping
	public void save(@RequestBody BoardSaveRequestDto board) {
		System.out.println(board.getTitle());
	}
}