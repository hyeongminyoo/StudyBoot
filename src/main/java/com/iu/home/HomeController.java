package com.iu.home;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.iu.home.board.qna.PostVO;
import com.iu.home.board.qna.QnaMapper;
import com.iu.home.board.qna.QnaVO;
import com.iu.home.member.MemberVO;
import com.iu.home.util.TestInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
	
//	@Value("${my.message.hi}")
	private String message;
	@Value("${my.default}")
	private String app;
	@Value("${kakao.client.id}")
	private String api;
	
//	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private QnaMapper qnaMapper;
	
	@GetMapping("/arrow")
	public void arrow() {
		//Lambda 식 (JS 에선 Arrow Function)
		TestInterface t = (m)->System.out.println(m);
		t.info("test");
		
		
		TestInterface t2 = new TestInterface() {
			
			@Override
			public void info(String message) {
				System.out.println(message);
				
			}
		};
		
		t2.info("test");
	}
	
	
	@GetMapping("/web")
	public String webClientTest() {
		
		WebClient webClient = WebClient.builder()
										.baseUrl("https://jsonplaceholder.typicode.com/")
										.build();
		Flux<PostVO> res = webClient.get()
				 					.uri("posts")
				 					.retrieve()
				 					.bodyToFlux(PostVO.class);
	
		PostVO postVO = res.blockFirst();
		
		//public void test(PostVO postVO){} 
		// a.test(postVO)
		
		res.subscribe((s) -> {
			PostVO pvo = s;
			log.info("ID : {}", s.getId());
		});
		
		log.info("Result => {}", postVO);
		
		return "";	
	}
	
	
	@GetMapping("/address")
	@ResponseBody
	public String address() throws Exception{
		//카카오 지도 요청
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK "+api);
		
		log.info("headers => {}", headers);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", "전북 삼성동 100");
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		ResponseEntity<String> response = restTemplate.getForEntity("https://dapi.kakao.com/v2/local/search/address.json", String.class,req);
		
		return response.getBody();
		
	}
	
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "Admin Role";
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "Manager Role";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String member() {
		return "Member Role";
	}
	
	
	@GetMapping("/")
	public String home(HttpSession session) throws Exception{
		
		RestTemplate restTemplate = new RestTemplate();
		
		//1. Header 
		HttpHeaders headers = new HttpHeaders();
		// header : key:value 형식
		//headers.add("key", "value");
		//headers.set헤더명("값);
		
		//2. parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("key", "value");
		
		//3. 요청정보를 담는 객체(1번, 2번을 모음)
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		//4. 전송 후 결과
		List<PostVO> postVOs = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class, request);
		
//		PostVO result = response.getBody();
//		log.info("response => {}", response);
		log.info("Posts=> {}", postVOs);
		
		log.info("======================================");
		Enumeration<String> en = session.getAttributeNames();
		
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			log.info("key : {}", key);
		}
		
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		if(context != null) {
			log.info("context => {}", context);
		}
		
		log.info("Info Message {} ",message);
		log.info("default {}",app);
		log.info("======================================");
		
//		List<QnaVO> ar = qnaMapper.getList();
		
//		log.info("List : {} size {}", ar, ar.size());
		
		return "index";
	}
}
