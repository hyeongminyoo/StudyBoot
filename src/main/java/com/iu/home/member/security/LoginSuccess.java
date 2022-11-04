package com.iu.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginSuccess implements AuthenticationSuccessHandler {
	
	
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("================로그인 성공 =====================");
		log.info("Authentication : {}", authentication);
		log.info("ID: {}", request.getParameter("id"));
			
		if(request.getParameter("rememberId") != null) {
			Cookie cookie = new Cookie("userId",  request.getParameter("id"));
			cookie.setHttpOnly(true);
			cookie.setMaxAge(60); //단위는 초
			cookie.setPath("/"); //같은 도메인 내에서 어느 URL에서 사용가능한지  
			response.addCookie(cookie);
		}else {
			Cookie [] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("userId")) {
					cookie.setMaxAge(0);
					cookie.setPath("/"); // 쿠키 삭제 시 쿠키 발행했을때 path와 동일해야함 아니면 삭제가 안됨
					response.addCookie(cookie);
					break;
				}
			}
		}
		
		response.sendRedirect("/");
	}
}
