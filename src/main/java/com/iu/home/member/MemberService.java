package com.iu.home.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	public int setJoin(MemberVO memberVO, Long [] nums) throws Exception{
		
		int result = memberMapper.setJoin(memberVO);
	
		for(int i = 0; i<nums.length; i++){
			RoleVO roleVO = new RoleVO();
			roleVO.setNum(nums[i]);
			roleVO.setId(memberVO.getId());
			memberMapper.setRole(roleVO);
		}
		
		return result;
	}
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception{
		return memberMapper.getLogin(memberVO);
	}
	
}
