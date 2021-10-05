package com.widus.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.widus.dto.chat.ChatMessageDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StompChatController {
	private final SimpMessagingTemplate template;
	
	@MessageMapping(value = "/chat/enter")
	public void enter(ChatMessageDto message) {
		message.setMessage(message.getWriter() + "님이 채팅방에 입장했습니다.");
		template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
	}
	
	@MessageMapping(value = "/chat/message")
	public void message(ChatMessageDto message) {
		template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
	}
}
