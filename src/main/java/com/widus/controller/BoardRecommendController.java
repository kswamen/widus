package com.widus.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.widus.dto.board.recommendation.RecommendId;
import com.widus.service.BoardRecommendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boardRecommend")
public class BoardRecommendController {
	private final BoardRecommendService boardRecommendService;
	
	@RequestMapping(value = "/recommend.do", method = {RequestMethod.POST, RequestMethod.PATCH})
	public void toggleOrRegisterRecommend(@RequestBody final RecommendId recommendId) {
		boardRecommendService.toggleOrRegisterRecommend(recommendId);

	}
	
	@RequestMapping(value = "/totalRecommend.do", method = {RequestMethod.GET})
	public int getTotalRecommen(@RequestParam final int boardId) {
		return boardRecommendService.getTotalRecommend(boardId);
	}
	
//	@RequestMapping(value = "/recommend.do", method = {RequestMethod.POST})
//	public void toggleOrRegisterRecommend(@RequestParam int boardId, @RequestParam String userEmail) {
//		System.out.println(boardId);
//		System.out.println(userEmail);
//	}
}
