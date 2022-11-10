package com.iu.home.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	@Value("${kakao.social.admin}")
	private String adminKey;
	
	public int setDelete(MemberVO memberVO) throws Exception{
		//1. WebClient를 생성
		WebClient webClient = WebClient.builder()
										.baseUrl("https://kapi.kakao.com/")
										.build();
		
		//2. paramete
//		Map<String, String> map = new HashMap<>();
//		map.put("target_id_type", "user_id");
//		map.put("target_id", memberVO.getId());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		params.add("target_id_type", "user_id");
		params.add("target_id", memberVO.getId());
		

		Mono<String> res = webClient.post()
				 .uri("v1/user/unlink")
				 //.bodyValue(params)
				 .body(BodyInserters.fromFormData(params))
				 .header("Authorization", "KakaoAK "+adminKey)
				 .header("Content-Type", "application/x-www-form-urlencoded")
				 .retrieve()
				 .bodyToMono(String.class);
		
		log.info("res => {}",res.block());
		
		return 1;
		
		
	}
	
	
	public int setDelete2(MemberVO memberVO) throws Exception{
		
		int result = 0;
		log.info("id => {}",memberVO.getId());
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Authorization", "KakaoAK "+adminKey);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		params.add("target_id_type", "user_id");
		params.add("target_id", memberVO.getId());
		
		//순서 파라미터 다음 헤더
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params,headers);
		
		ResponseEntity<String> res = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/unlink", req, String.class);
		
		log.info("res => {}",res.getBody());
		
		
		if(res.getBody() != null) {
			result=1;
		}
		return result;
		
	}
	
	
	//사용자 정의 검증 메서드
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean check = false;
		//check == false : 검증성공(에러가 없음)
		//check == true : 검증 실패(error 있음)
		//password가 일치하는지 검증
		
		//1. annotation 검증
		check = bindingResult.hasErrors();
		
		
		//2. password가 일치하는지 검증
		if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
			check = true;
			//에러메세지 
//			bindingResult.rejectValue("멤버변수명(path)", "properties의 key(코드)");
			bindingResult.rejectValue("pwCheck", "member.password.notEqual");
		}
		
		if(memberMapper.getIdCheck(memberVO.getId())>0) {
			check = true;
			bindingResult.rejectValue("id", "member.id.duplicated");
		}
		
		
		return check;
	}
	
	
	public int setJoin(MemberVO memberVO) throws Exception{
		
		int result = memberMapper.setJoin(memberVO);
		
		if(result<1) {
			throw new Exception();
		}
		
		result = memberMapper.setMemberRole(memberVO);
		
		if(result<1) {
			throw new Exception();
		}
		
		return result;
	}
	
//  로그인 처리는 Security에서 
//	public MemberVO getLogin(MemberVO memberVO) throws Exception{
//		return memberMapper.getLogin(memberVO);
//	}
	
	public int getIdCheck(String id) throws Exception{
		return memberMapper.getIdCheck(id);
	}
	
}
