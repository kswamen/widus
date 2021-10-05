package com.widus.dto.chat;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roomId;
	private String name;
	private Set<WebSocketSession> sessions = new HashSet<>();
	
	public static ChatRoomDto create(String name) {
		ChatRoomDto room = new ChatRoomDto();
		
		room.roomId = UUID.randomUUID().toString();
		room.name = name;
		return room;
	}
}
