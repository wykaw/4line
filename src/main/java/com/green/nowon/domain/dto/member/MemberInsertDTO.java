package com.green.nowon.domain.dto.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.MemberEntity;

import lombok.Setter;

@Setter
public class MemberInsertDTO {
	
	private String email;
	private String pass;
	private String nickName;
	
	public MemberEntity entity(PasswordEncoder pe) {
		return MemberEntity.builder().email(email).pass(pe.encode(pass)).nickName(nickName).build();
	}
	
}
