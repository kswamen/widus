package com.widus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.widus.dto.comment.Comment;
import com.widus.dto.user.User;
import com.widus.paging.Criteria;
import com.widus.paging.PagingDto;
import com.widus.service.BoardService;
import com.widus.utils.UiUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController extends UiUtils {
	private final BoardService boardService;

	@GetMapping(value = "/board_list.do")
	public String openBoard_list(@LoginUser SessionUser user, @RequestParam("division") BoardRole role, Criteria criteria, Model model) {
		model.addAttribute("divisionList", BoardRole.values());
		if (user != null) {
			model.addAttribute("user", user);
		}
		model.addAttribute("division", role);
		
		Page<Board> boardList = boardService.getBoardByRole(role, criteria);
		model.addAttribute("boardList", boardList);
		
		PagingDto pagingDto = boardService.getPagingDto(role, criteria);
		model.addAttribute("pagingDto", pagingDto);
		
//		System.out.println(pagingDto.getCriteria().getCurrentPageNo());

		return "board/board_list";
	}
	
	@GetMapping(value = "/board_test_list.do")
	public String board_test() {
		PageRequest pageRequest = PageRequest.of(1, 10);
		Page<Board> board = boardService.findAll(pageRequest);
		
		List<Board> list = board.getContent();
	    list.forEach(b -> System.out.println(b.getId() + " " + b.getTitle()));

		return "index";
	}

	@GetMapping(value = "/board_write.do")
	public String openBoard_write(@LoginUser SessionUser user, @RequestParam(value = "id", required = false) Long idx,
			@RequestParam(value = "division", required = false) BoardRole role, Model model) {
		model.addAttribute("divisionList", BoardRole.values());
		model.addAttribute("division", role);
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
		model.addAttribute("boardId", idx);
		Board board = boardService.getBoardById(idx).get();
		model.addAttribute("board", board);
		User wroteUser = boardService.getWroteUser(board.getEmail());
		model.addAttribute("wroteUser", wroteUser);
		
		return "board/board_detail";
	}
	
	@GetMapping(value = "/board_delete.do") 
	public String openBoard_delete(@RequestParam(value = "id", required = true) Long idx, Model model) {
		Map<String, Object> pagingParams = new HashMap<>();
		try {
			Board board = boardService.deleteBoard(idx);
			if (board == null) {
				return showMessageWithRedirect("게시글 삭제에 실패했습니다.", "/", Method.GET, null, model);
			}
			pagingParams.put("division", board.getRole());
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정 중 문제가 발생했습니다.", "/", Method.GET, null, model);
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생했습니다.", "/", Method.GET, null, model);
		}

		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/board/board_list.do", Method.GET, pagingParams, model);
	}
	
//	@PostMapping(value = "/board_update.do")
//	public String openBoard_update(@LoginUser SessionUser user, Long id, BoardUpdateRequestDto board, Model model) {
//		try {
//			Board savedBoard = boardService.updateBoard(board, id);
//			if (savedBoard == null) {
//				return showMessageWithRedirect("게시글 업데이트에 실패했습니다.", "/", Method.GET, null, model);
//			}
//		} catch (DataAccessException e) {
//			return showMessageWithRedirect("데이터베이스 처리 과정 중 문제가 발생했습니다.", "/", Method.GET, null, model);
//		} catch (Exception e) {
//			return showMessageWithRedirect("시스템에 문제가 발생했습니다.", "/", Method.GET, null, model);
//		}
//
//		return showMessageWithRedirect("게시글 업데이트가 완료되었습니다.", "/", Method.GET, null, model);
//	}

	@PostMapping(value = "/register.do")
	public String board_write(@LoginUser SessionUser user, Long id, BoardSaveRequestDto board, Model model) {
		// System.out.println(board.getId());
		Map<String, Object> pagingParams = new HashMap<>();
		
		try {
//			System.out.println(board.getWriter());
//			System.out.println(board.getContent());
//			System.out.println(board.getEmail());
//			System.out.println(board.getTitle());
//			System.out.println(board.getRole());
//			System.out.println(board.getThumbnail());
			Board savedBoard = null;
			pagingParams.put("division", board.getRole());
			if (id == null) {
				savedBoard = boardService.saveBoard(board);
			}
			else {
				savedBoard = boardService.saveBoard(board, id);
			}
			
			if (savedBoard == null) {
				return showMessageWithRedirect("게시글 등록에 실패했습니다.", "/", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정 중 문제가 발생했습니다.", "/", Method.GET, null, model);
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생했습니다.", "/", Method.GET, null, model);
		}

		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/board/board_list.do", Method.GET, pagingParams, model);
	}
}
