package com.widus.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.widus.dto.board.recommendation.BoardRecommend;
import com.widus.dto.board.recommendation.BoardRecommendRepository;
import com.widus.dto.board.recommendation.RecommendId;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardRecommendService {
	private final BoardRecommendRepository boardRecommendRepository;
	
	@Transactional
	public void toggleOrRegisterRecommend(RecommendId recommendId) {
		Optional<BoardRecommend> boardRecommend = boardRecommendRepository.findById(recommendId);
		// 만약 아직 등록되지 않은 (이메일, 보드 ID) 일 경우, 추천된 상태로 등록
		if (boardRecommend.equals(Optional.empty())) {
			boardRecommendRepository.save(BoardRecommend.builder()
					.boardId(recommendId.getBoardId())
					.userEmail(recommendId.getUserEmail())
					.recommended(1)
					.build());
		}
		else {
			boardRecommend.get().toggleRecommend();
			boardRecommendRepository.save(boardRecommend.get());
		}
	}
	
	@Transactional
	public int getTotalRecommend(int boardId) {
		int result = boardRecommendRepository.getRecommendCount(boardId);
		return result;
	}
}
