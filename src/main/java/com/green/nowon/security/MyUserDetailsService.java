package com.green.nowon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.green.nowon.domain.entity.MemberEntityRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	MemberEntityRepository mRepository;
	
	//DB의 테이블에서 인증처리를 하기 위한 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//소셜로그인을 인증하는 메소드사용 기존의  findbyemail제거
		return new MyUserDetails(mRepository.findByEmailAndSocial(username,false)
				.orElseThrow(()->new UsernameNotFoundException("존재하지 않는 이메일")));
//		return new MyUserDetails(mRepository.findByEmail(username)
//		.orElseThrow(()->new UsernameNotFoundException("존재하지 않는 이메일")));
	}
}