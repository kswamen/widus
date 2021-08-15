package com.widus.dto.jpatestcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("memberTest")
public class MemberContoller {
	@Autowired
	MemberService memberService;
	
	@PostMapping
	public ResponseEntity<MemberVo> save(MemberVo member) {
		System.out.println(member.getMbrNo());
		System.out.println(member.getId());
		System.out.println(member.getName());
		return new ResponseEntity<MemberVo>(memberService.save(member), HttpStatus.OK);
	}
}