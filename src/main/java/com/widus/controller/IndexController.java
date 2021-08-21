package com.widus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
   
   @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
   public String loginPage(HttpServletRequest request, @RequestParam(value="prevPage", required=false)String prevPage, Model model) {
	   model.addAttribute("divisionList", BoardRole.values());
//       String referrer = request.getHeader("Referer");
//       System.out.println(referrer);
	   if (prevPage == "") {
		   prevPage = "/";
	   }
	   System.out.println(prevPage);
       request.getSession().setAttribute("prevPage", prevPage);
       return "loginPage";
   }
  
}