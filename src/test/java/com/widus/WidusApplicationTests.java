package com.widus;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.widus.dto.board.BoardRepository;

@SpringBootTest
class WidusApplicationTests {
	@Autowired
	private BoardRepository boardRepository;

	@Test
	void contextLoads() {
	}

}
