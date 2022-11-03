package com.iu.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig   {

	@Bean
	//public을 선언하면 default로 변경하라는 메세지 출력
	WebSecurityCustomizer webSecurityConfig() {
		
		//Security에서 무시해야하는 url 패턴 등록
		return web -> web
				.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/favicon/**")
				.antMatchers("/resources/**");
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
					.cors()
					.and()
					.csrf()
					.disable()
				.authorizeRequests()
					.antMatchers("/admin").hasRole("ADMIN")
					.antMatchers("/manager").hasAnyRole("ADMIN","MANAGER")
					.antMatchers("/qna/list").permitAll()
					.antMatchers("/qna/**").authenticated()
					.anyRequest().permitAll()
					.and()
				.formLogin()		//로그인 폼 인증 설정
					.loginPage("/member/login")  // 내장된 로그인 폼을 사용하지않고 개발자가만든 폼 요청
					//.loginProcessingUrl("login")    // 로그인을 진행 요청할 form 태그의 action의 주소 지정
					.passwordParameter("pw")     // 패스워드 파라미터는 password이지만 개발자가 다른 파라미터 이름을 사용할 때
					.usernameParameter("id")	 // 아이디 파라미터는 username이지만 개발자가 다른 파라미터 이름을 사용할 때
					.defaultSuccessUrl("/")  	 // 인증에 성공할 경우 요청할 URL
					.failureUrl("/member/login") // 인증에 실패했을 경우 요청할 URL
					.permitAll()
					.and()
				.logout()
					.logoutUrl("/member/logout")
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.permitAll();
		
		return httpSecurity.build();
	}
	
	//평문(Clear Text)을 암호화 시켜주는 객체생성
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}