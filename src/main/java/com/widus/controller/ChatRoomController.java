package com.widus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.widus.auth.LoginUser;
import com.widus.auth.SessionUser;
import com.widus.dto.chat.ChatRoomRepository;
import com.widus.utils.UiUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController extends UiUtils {
	private final ChatRoomRepository repository;	
	
	// 채팅방 목록
	@GetMapping(value = "/rooms")
	public ModelAndView openChatRoom_list(@LoginUser SessionUser user) {
		ModelAndView mv = new ModelAndView("chat/rooms");
		mv.addObject("list", repository.findAllRooms());
		mv.addObject("user", user);
		
		return mv;
	}
	// 채팅방 개설
	@PostMapping(value = "/room")
	public String create(@RequestParam String name, RedirectAttributes rttr) {
		rttr.addFlashAttribute("roomName", repository.createChatRoomDto(name));
		return "redirect:/chat/rooms";
	}
	
	// 채팅방 조회
	@GetMapping("/room")
	public void getRoom(@LoginUser SessionUser user, String roomId, Model model) {
		model.addAttribute("room", repository.findRoomById(roomId));
		model.addAttribute("user", user);
	}
}
