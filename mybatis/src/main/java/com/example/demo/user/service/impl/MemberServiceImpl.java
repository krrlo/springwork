package com.example.demo.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.user.mapper.MemberMapper;
import com.example.demo.user.service.MemberService;
import com.example.demo.user.service.MemberVO;
import com.example.demo.user.service.UserDetailVO;

@Service  //얘해주면 시큐리티가 알아서 써냄 
public class MemberServiceImpl implements MemberService , UserDetailsService {

	@Autowired MemberMapper mapper;
	
	
	@Override
	public MemberVO getMember(String id) {
		
		return mapper.getMember(id);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//p670
		MemberVO vo = mapper.getMember(username);
		if (vo == null) {
			throw new UsernameNotFoundException("no name");
		}
		return new UserDetailVO(vo);
	}
	
	

}
