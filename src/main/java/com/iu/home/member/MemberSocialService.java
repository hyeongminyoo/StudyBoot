package com.iu.home.member;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class MemberSocialService extends DefaultOAuth2UserService {
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("=================Socail Login 시도=================");
		log.info("userRequest => {}",userRequest);
		log.info("AccessToken : {}",userRequest.getAccessToken());
		log.info("AdditionalParameters : {}",userRequest.getAdditionalParameters());
		log.info("ClientRegistration : {}",userRequest.getClientRegistration());
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("========================사용자정보=======================");
		log.info("Name => {}", oAuth2User.getName());
		log.info("Attr => {}", oAuth2User.getAttributes());
		log.info("Auth => {}",oAuth2User.getAuthorities());
		return null;
	}
	
	//회원가입 유무를 판단해서 처음온사람이면 회원가입 진행
	private OAuth2User socialJoinCheck(OAuth2UserRequest userRequest) {
		return null;
	}
	
}
