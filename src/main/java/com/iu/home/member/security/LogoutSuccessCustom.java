package com.iu.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.iu.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class LogoutSuccessCustom implements LogoutSuccessHandler {
	
	@Value("${kakao.client.id}")
	private String clientId;
	@Value("${kakao.logout.redirect.uri}")
	private String redirectUri;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		String social = memberVO.getSocial();
		
		if(social != null) {
			if(social.equals("kakao")) {
							
				//response.sendRedirect("https://developers.kakao.com/logout");
				//response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+clientId+"&logout_redirect_uri="+redirectUri);
				RestTemplate restTemplate = new RestTemplate();
				//header X
				//parameter X
				MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
				map.add("client_id", clientId);
				
				HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String,String>>(map, null);
				
				ResponseEntity<String> res = restTemplate.getForEntity("https://developers.kakao.com/logout",null,String.class);
				log.info("res => {}",res);
				response.sendRedirect("/");
				
			}else if(social.equals("google")) {
				
			}else {
				
			}
		}else {
			
			log.info("=================로그아웃 성공했을때==========================");
			response.sendRedirect("/");
		}
		
		
		
		
	}
}
