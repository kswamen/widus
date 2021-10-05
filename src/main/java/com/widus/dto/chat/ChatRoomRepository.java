package com.widus.dto.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepository {
	private Map<String, ChatRoomDto> chatRoomDtoMap;
	
	@PostConstruct
	private void init() {
		chatRoomDtoMap = new LinkedHashMap<> ();
	}
	
	public List<ChatRoomDto> findAllRooms() {
		List<ChatRoomDto> result = new ArrayList<>(chatRoomDtoMap.values());
		Collections.reverse(result);
		
		return result;
	}
	
	public ChatRoomDto findRoomById(String id) {
		return chatRoomDtoMap.get(id);
	}
	
	public ChatRoomDto createChatRoomDto(String name) {
		ChatRoomDto room = ChatRoomDto.create(name);
		chatRoomDtoMap.put(room.getRoomId(), room);
		
		return room;
	}
}
