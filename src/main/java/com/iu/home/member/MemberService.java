package com.iu.home.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
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
