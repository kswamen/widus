package com.widus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.widus.auth.LoginUser;
import com.widus.auth.SessionUser;
import com.widus.dto.board.BoardRole;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping(value="/board_list.do")
	public String openBoard_list(@LoginUser SessionUser user, @RequestParam("division") BoardRole role, Model model)  {
		model.addAttribute("divisionList", BoardRole.values());
		if(user != null) {
			model.addAttribute("user", user);
		}
		model.addAttribute("division", role);
		
		return "board/board_list";
	}
	
	@GetMapping(value="/board_write.do")
	public String openBoard_write(@LoginUser SessionUser user, Model model)  {
		model.addAttribute("divisionList", BoardRole.values());
		if(user != null) {
			model.addAttribute("user", user);
		}
		
		return "board/board_write";
	}
	
}
