package com.widus.controller;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.widus.auth.LoginUser;
import com.widus.auth.SessionUser;
import com.widus.constant.Method;
import com.widus.dto.board.Board;
import com.widus.dto.board.BoardRole;
import com.widus.dto.board.BoardSaveRequestDto;
import com.widus.service.BoardService;
import com.widus.utils.UiUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController extends UiUtils {
	private final BoardService boardService;

	@GetMapping(value = "/board_list.do")
	public String openBoard_list(@LoginUser SessionUser user, @RequestParam("division") BoardRole role, Model model) {
		model.addAttribute("divisionList", BoardRole.values());
		if (user != null) {
			model.addAttribute("user", user);
		}
		model.addAttribute("division", role);

		List<Board> boardList = boardService.getBoardByParams(role);
		model.addAttribute("boardList", boardList);

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

	@GetMapping(value = "/board_detail.do")
	public String openBoard_detail(@LoginUser SessionUser user, @RequestParam(value = "id", required = true) Long idx,
			Model model) {
		model.addAttribute("divisionList", BoardRole.values());
		if (user != null) {
			model.addAttribute("user", user);
		}
		Board board = boardService.getBoardById(idx).get();
		model.addAttribute("board", board);
		
		return "board/board_detail";
	}

	@PostMapping(value = "/register.do")
	public String board_write(@LoginUser SessionUser user, BoardSaveRequestDto board, Model model) {
		// System.out.println(board.getId());
		try {
//			System.out.println(board.getWriter());
//			System.out.println(board.getContent());
//			System.out.println(board.getEmail());
//			System.out.println(board.getTitle());
//			System.out.println(board.getRole());
//			System.out.println(board.getThumbnail());

			Board savedBoard = boardService.saveBoard(board.toEntity());
			if (savedBoard == null) {
				return showMessageWithRedirect("게시글 등록에 실패했습니다.", "/", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정 중 문제가 발생했습니다.", "/", Method.GET, null, model);
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생했습니다.", "/", Method.GET, null, model);
		}

		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/", Method.GET, null, model);
	}
}
