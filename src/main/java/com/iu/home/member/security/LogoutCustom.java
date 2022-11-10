package com.iu.home.member.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.iu.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutCustom implements LogoutHandler {
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		log.info("==============로그아웃 핸들러=======================");
		log.info("auth => {}", authentication);
		//1. 일반 로그인 UsernamePasswordAuthenticationToken 2.소셜로그인 사용 
		
		
		
//		if(social != null && social.equals("kakao")) {
//			
//		}else if(social != null && social.equals("google")) {
//		
//		}else {
//			
//		}

		request.getSession().invalidate();
		
		
	}
	
}
