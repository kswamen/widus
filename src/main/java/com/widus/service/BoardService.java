package com.widus.service;

import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.widus.dto.board.Board;
import com.widus.dto.board.BoardRepository;
import com.widus.dto.board.BoardRole;
import com.widus.dto.board.BoardSaveRequestDto;
import com.widus.dto.user.User;
import com.widus.dto.user.UserRepository;
import com.widus.paging.Criteria;
import com.widus.paging.PagingDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	public Optional<Board> getBoardById(long id) {
		return boardRepository.findById(id);
	}
	
	public Page<Board> findAll(PageRequest pageRequest) {
		return boardRepository.findAll(pageRequest);
	}
	
	@Transactional
	public Board saveBoard(BoardSaveRequestDto board) {
		board.setThumbnail(getThumbnailSrc(board.getContent()));
		board.setUser(userRepository.findById(board.getEmail()).get());
		return boardRepository.save(board.toEntity());
	}
	
	@Transactional
	public Board saveBoard(BoardSaveRequestDto boardSaveRequestDto, Long id) {
		Board board = boardRepository.findById(id).get();
//		System.out.println(board.getContent());
//		System.out.println(boardSaveRequestDto.getContent());
		
		boardSaveRequestDto.setThumbnail(getThumbnailSrc(boardSaveRequestDto.getContent()));

		board.boardUpdate(
				boardSaveRequestDto.getTitle(),
				boardSaveRequestDto.getContent(), 
				boardSaveRequestDto.getRole(), 
				boardSaveRequestDto.getThumbnail());
		return boardRepository.save(board);
	}
	
	public String getThumbnailSrc(String html) {
		Document doc = Jsoup.parseBodyFragment(html);
		String result = "/common/ckDownload/placeholder-image.jpg";
		
		Element img = doc.getElementsByTag("img").first();
		if (img != null) {
			result = img.attr("src");
		}

		return result;
	}

//	@Transactional
//	public Page<Board> getBoardByRole(BoardRole role, Pageable pageable) {
//		return boardRepository.findByRole(role, pageable);
//	}
	
	public Page<Board> getBoardByRole(BoardRole role, Criteria criteria) {
		// JPA??? ???????????? 0?????? ???????????? ????????? 1??? ??? ??????.
		PageRequest pageRequest = PageRequest.of(criteria.getCurrentPageNo() - 1, criteria.getRecordsPerPage());
		//PageRequest pageRequest = PageRequest.of(0, 10);
//		System.out.println(criteria.getCurrentPageNo() + " " + criteria.getRecordsPerPage());
		Page<Board> pageBoard = boardRepository.findByRole(role, pageRequest);
		
//		List<Board> list = pageBoard.getContent();
//	    list.forEach(b -> System.out.println(b.getId() + " " + b.getTitle()));
		
		return pageBoard;
	}
	
	public PagingDto getPagingDto(BoardRole role, Criteria criteria) {
		int boardTotalCnt = boardRepository.getRecordCount(role);
		PagingDto pagingDto = new PagingDto(criteria);
		pagingDto.setTotalRecordCount(boardTotalCnt);
		
		return pagingDto;
	}
	

	@Transactional
	public Board deleteBoard(Long id) {
		Board board = boardRepository.findById(id).get();
		board.boardDelete();
		
		return boardRepository.save(board);
	}
	
	@Transactional
	public Board updateVisitBoard(Long id) {
		Board board = boardRepository.findById(id).get();
		board.boardVisitPlus();
		
		return boardRepository.save(board);
	}
}
