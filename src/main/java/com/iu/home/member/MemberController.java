package com.iu.home.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("login")
	public String getLogin() throws Exception{
		return "member/login";
	}
	
	@PostMapping("login")
	public ModelAndView getLogin(MemberVO memberVO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberService.getLogin(memberVO);
		
		session.setAttribute("member", memberVO);
		
		int result = 0;
		String message = "로그인 실패";
		String url = "./login";
		if(memberVO != null) {
			result = 1;
			message = "로그인 성공";
			url = "../";
		}
		mv.addObject("result", result);
		mv.addObject("message", message);
		mv.addObject("url", url);
		mv.setViewName("common/result");
		
		return mv;
		
	}
	
	@GetMapping("join")
	public String setJoin() throws Exception{
		return "member/join";
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = memberService.setJoin(memberVO);
		
		String message = "";
		String url ="./join";
		if(result == 1) {
			message = "회원가입 성공";
			url = "./login";
		}
		mv.addObject("url", url);
		mv.addObject("result", result);
		mv.addObject("message", message);
		mv.setViewName("common/result");
		
		return mv;
	}
	
	@GetMapping("logout")
	public String getLogOut(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	
	@PostMapping("idCheck")
	@ResponseBody
	public int getIdCheck(String id) throws Exception{
		int result = memberService.getIdCheck(id);
		if(result > 0) {
			result = 1;
		}else {
			result = 0;
		}
		
		return result;
	}
	
}
