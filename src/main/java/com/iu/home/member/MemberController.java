package com.iu.home.member;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("delete")
	public ModelAndView setDelete(HttpSession session, String password, HttpServletRequest request, HttpServletResponse response) throws Exception{
		// 1. 소셜로그인 / 일반 로그인 구분
		ModelAndView mv = new ModelAndView();
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication authentication = context.getAuthentication();
		
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
	
		int result = memberService.setDelete(memberVO);
		
		if(result > 0) {
			session.invalidate();
			Cookie [] cookies = request.getCookies();
			
			for(Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			mv.setViewName("redirect:/");
//			redirectAttributes.addFlashAttribute("authentication", authentication);
//			return "redirect:../member/logout";
		}else {
			//탈퇴 실패
//			return "redirect:/";
		}
		
		return mv;
		
	}
	
	@GetMapping("login")
	public String getLogin(@RequestParam(defaultValue = "false", required = false) boolean error, String message, Model model) throws Exception{
		if(error) {
			model.addAttribute("msg", "ID 또는 PW를 확인하세요");
		}
		//Controller에서 받아서 jsp로 다시 보내도됨
		return "member/login";
	}
	
	@PostMapping("login")
	public String getLogin(MemberVO memberVO, HttpSession session) throws Exception{
		log.info("============ Login Post ================");
//		ModelAndView mv = new ModelAndView();
//		memberVO = memberService.getLogin(memberVO);
//		
//		session.setAttribute("member", memberVO);
//		
//		int result = 0;
//		String message = "로그인 실패";
//		String url = "./login";
//		if(memberVO != null) {
//			result = 1;
//			message = "로그인 성공";
//			url = "../";
//		}
//		mv.addObject("result", result);
//		mv.addObject("message", message);
//		mv.addObject("url", url);
//		mv.setViewName("common/result");
		
		return "member/login";
		
	}
	
	@GetMapping("join")
	public String setJoin(@ModelAttribute MemberVO memberVO) throws Exception{
//		MemberVO memberVO = new MemberVO();
//		model.addAttribute("vo",memberVO);
		return "member/join";
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(@Valid MemberVO memberVO, BindingResult bindingResult ,ModelAndView mv) throws Exception{
//		if(bindingResult.hasErrors()) {
//			//검증에 실패하면 회원가입하는 jsp로 forward
//			log.info("======== 검증 에러 발생 =======");
//			
//			mv.setViewName("member/join");
//			return mv;
//		}

		boolean check = memberService.getMemberError(memberVO, bindingResult);
		if(check) {
			//검증에 실패하면 회원가입하는 jsp로 forward
			log.info("======== 검증 에러 발생 =======");
			
			mv.setViewName("member/join");
			//===================================='\
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for(FieldError fieldError : fieldErrors) {
				log.info("FieldError => {}", fieldError);
				log.info("Field => {}", fieldError.getField());
				log.info("Message => {}", fieldError.getRejectedValue());
				log.info("Default => {}", fieldError.getDefaultMessage());
				log.info("Code => {}", fieldError.getCode());
				mv.addObject(fieldError.getField(), fieldError.getDefaultMessage());
				log.info("==================================================");
			}
			
			return mv;
		}
//		int result = memberService.setJoin(memberVO);
		
//		String message = "";
//		String url ="./join";
//		if(result == 1) {
//			message = "회원가입 성공";
//			url = "./login";
//		}
//		mv.addObject("url", url);
//		mv.addObject("result", result);
//		mv.addObject("message", message);
//		mv.setViewName("common/result");
		
		mv.setViewName("redirect:../");
		return mv;
	}
	
//	@GetMapping("logout")
//	public String getLogOut(HttpSession session) throws Exception{
//		log.info("========= 내가만든 logout 메서드 ==========");
//		
//		session.invalidate();
//		return "redirect:../";
//	}
	
	@GetMapping("idCheck")
	@ResponseBody
	public int getIdCheck(String id) throws Exception{
		int result = memberService.getIdCheck(id);
		
		return result;
	}
	
	@GetMapping("mypage")
	public void getMypage() throws Exception{
		
	}
	
	@GetMapping("/logoutResult")
	public String socialLogout() throws Exception{
		return "redirect:../";
	}
	
}
