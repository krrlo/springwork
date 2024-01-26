package com.example.demo.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  //생성자 만들어주는 자동으로

public class UserDetailVO implements UserDetails{
	
	final MemberVO memberVO;
	
	
//	private MemberVO memberVO;
//
//	
//	public UserDetailVO() {}  // 기본생성자
//	
//	public void UserDetailVO (MemberVO memberVO) {
//		this.memberVO = memberVO;
//	}
//	
//	public void setMemberVO(MemberVO memberVO) {
//		this.memberVO = memberVO;
//	}
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();  //한유저가 여러개 권한을 가질수있으므로 
		list.add(new SimpleGrantedAuthority(memberVO.getResponsibility()));
		
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return memberVO.getPass();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return memberVO.getMid();
	}

	@Override  
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override	//로그인 막게..하는거래  
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
