package com.widus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.widus.auth.LoginUser;
import com.widus.auth.SessionUser;
import com.widus.dto.board.BoardRole;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
   @GetMapping("/")
   public String index(Model model, @LoginUser SessionUser user) {
	   model.addAttribute("divisionList", BoardRole.values());
	   if(user != null){
           model.addAttribute("user", user);
       }

       return "index";
   }
}