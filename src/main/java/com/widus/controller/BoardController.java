package com.widus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.widus.auth.LoginUser;
import com.widus.auth.SessionUser;
import com.widus.dto.board.Board;
import com.widus.dto.board.BoardRole;
import com.widus.dto.board.BoardSaveRequestDto;
import com.widus.dto.board.TestDto;
import com.widus.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;

	@GetMapping(value = "/board_list.do")
	public String openBoard_list(@LoginUser SessionUser user, @RequestParam("division") BoardRole role, Model model) {
		model.addAttribute("divisionList", BoardRole.values());
		if (user != null) {
			model.addAttribute("user", user);
		}
		model.addAttribute("division", role);

		return "board/board_list";
	}

	@GetMapping(value = "/board_write.do")
	public String openBoard_write(@LoginUser SessionUser user, @RequestParam(value = "id", required = false) Long idx,
			Model model) {
		model.addAttribute("divisionList", BoardRole.values());
		if (user != null) {
			model.addAttribute("user", user);
		}
		if (idx == null) {
			model.addAttribute("board", new Board());
		} else {
			Board board = boardService.getBoardById(idx).get();
			model.addAttribute("board", board);
		}

		return "board/board_write";
	}

	@PostMapping(value = "/register.do")
	public String board_write(@LoginUser SessionUser user, BoardSaveRequestDto board) {
		//System.out.println(board.getId());
		boardService.saveBoard(board.toEntity());

		return "test";
	}
	
	@PostMapping(value = "/test.do")
	public String test(@LoginUser SessionUser user, TestDto t) {
		System.out.println(t.getId());
		System.out.println(t.getPwd());

		return "test";
	}
}
