package com.widus.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.widus.dto.board.BoardRepository;
import com.widus.dto.board.recommendation.BoardRecommend;
import com.widus.dto.board.recommendation.BoardRecommendRepository;
import com.widus.dto.board.recommendation.RecommendId;
import com.widus.dto.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardRecommendService {
	private final BoardRecommendRepository boardRecommendRepository;
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	
	@Transactional
	public void toggleOrRegisterRecommend(RecommendId recommendId) {
		Optional<BoardRecommend> boardRecommend = boardRecommendRepository.findById(recommendId);
		// 만약 아직 등록되지 않은 (이메일, 보드 ID) 일 경우, 추천된 상태로 등록
		if (boardRecommend.equals(Optional.empty())) {
			boardRecommendRepository.save(BoardRecommend.builder()
					.boardId(boardRepository.findById(recommendId.getBoardId()).get())
					.userEmail(userRepository.findById(recommendId.getUserEmail()).get())
					.recommended(1)
					.build());
		}
		else {
			boardRecommend.get().toggleRecommend();
			boardRecommendRepository.save(boardRecommend.get());
		}
	}
	
	public int getTotalRecommend(Long boardId) {
		int result = boardRecommendRepository.getRecommendCount(boardRepository.findById(boardId).get());

		return result;
	}
}
